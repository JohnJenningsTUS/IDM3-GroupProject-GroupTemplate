# Setup — Command Line

Get the app running, tests passing, and Git working from the terminal with the editor of your choice. Prefer an IDE to do it for you? Use [SETUP-INTELLIJ.md](SETUP-INTELLIJ.md) instead.

These are also the exact commands CI runs, so this guide is the reference when a PR goes red.

## Before you start

You need three things installed:

| Tool | Check it works | Notes |
| --- | --- | --- |
| **JDK 17** | `java -version` → `17.x` | Same version as the Dockerfile and CI. [Adoptium](https://adoptium.net/) is the easy install |
| **Docker** | `docker --version` | Docker Desktop on Windows/macOS; Docker Engine on Linux |
| **Git** | `git --version` | — |

You do **not** need Maven — the repo ships the Maven wrapper (`mvnw`), which downloads the right version itself.

> **Windows**: every `./mvnw` below is `.\mvnw.cmd` in PowerShell or CMD. In Git Bash, `./mvnw` works as written.

## 1. Clone and switch to `develop`

```bash
git clone git@github.com:<org>/<team-repo>.git
cd <team-repo>
git switch develop
```

## 2. Start MySQL and phpMyAdmin

```bash
docker compose up -d db phpmyadmin
```

Confirm both are up with `docker compose ps` — `db` should say **healthy**.

The first time `db` starts, it loads the sample data in `idm3project2024.sql`: users, projects and showcases. That only happens when the database volume is empty — see [Reset the database](#reset-the-database) below.

### phpMyAdmin

Open http://localhost:8081. It logs you straight in as the app's database user, so you can browse and edit the tables in `idm3project2024` — handy for checking what your code actually saved.

| Setting | Value |
| --- | --- |
| Host / port | `localhost:3306` (`db:3306` from inside Docker) |
| Database | `idm3project2024` |
| App user | `gallery` / `gallerypass` |
| Root user | `root` / `rootpass` |

phpMyAdmin is for your laptop only. It's never deployed to test or prod.

## 3. Run the app

```bash
./mvnw spring-boot:run
```

The `dev` profile is the default, so no extra flags. To be explicit, or to run another profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Open http://localhost:8080 — it redirects to the gallery home page, which lists the sample projects and showcases. The footer should read **TUS Gallery Build info: local dev**.

Stop it with **Ctrl+C**.

### Test logins

Sign in at http://localhost:8080/MainGallery/Login with any of these accounts from the sample data:

| Role | Username | Password |
| --- | --- | --- |
| Student | `TRYAN` | `pass123` |
| Student | `MSith` | `123pass` |
| Administrator | `tsmith` | `pass123` |
| Administrator | `EBourke` | `Bourke123` |
| Employer (viewer) | `PCbus` | `PC123` |

There's no Senior Administrator in the sample data yet.

> **A 404 after signing in means it worked.** A successful login sends you to `/dashboard`, which hasn't been built yet. A wrong password keeps you on the login page instead.

These passwords are stored as plain text — local dev only. Real authentication with hashed passwords is part of the Week 2–4 work.

You can also register your own account at http://localhost:8080/MainGallery/Register. New accounts are students.

## 4. Run the tests

`db` must be running — the tests start the whole app, including its database connection.

```bash
./mvnw test
```

All green means your dev environment works.

A single test class, while you're working:

```bash
./mvnw test -Dtest=GalleryApplicationTests
```

> **Checkpoint ✅** App running locally, test logins working, tests passing.

## The command CI runs

```bash
./mvnw verify
```

This is what decides whether your PR goes green — it compiles, runs tests, and packages the app. CI starts its own empty MySQL for this, so don't write tests that rely on the sample data. Run it before you push and you'll catch most red builds on your own machine.

## Reset the database

To throw away your local data and reload the sample data:

```bash
docker compose down -v
docker compose up -d db phpmyadmin
```

`-v` deletes the database volume — everything you've added locally is gone. Do this when your data gets into a mess, or after a teammate changes `idm3project2024.sql`.

## Day-to-day Git

```bash
# Get latest develop, then branch from it
git switch develop
git pull
git switch -c feature/1-short-description

# See what you changed
git status
git diff

# Stage, commit, push
git add src/main/resources/templates/homepage.html
git commit -m "Short description of the change (#1)"
git push -u origin feature/1-short-description
```

Then open the PR on GitHub — into `develop`, never `main`.

**Reviewing a teammate's PR** means running their code, not just reading it:

```bash
git fetch origin
git switch feature/2-their-branch
./mvnw spring-boot:run
```

Optional but worth it: the [GitHub CLI](https://cli.github.com/) (`gh`) lets you do the whole PR cycle from the terminal — `gh pr create`, `gh pr checkout <number>`, `gh pr review`, `gh run watch` to follow CI live.

## Troubleshooting

| Symptom | Fix |
| --- | --- |
| `mvnw: Permission denied` | `chmod +x mvnw` (macOS/Linux, first clone only) |
| `maven-wrapper.properties: No such file or directory` | The hidden `.mvn` folder is missing — usually from copying the project instead of cloning it. Clone it again, or copy `.mvn` across |
| `Invalid source release: 17` | Wrong JDK on your PATH. Check `java -version`; set `JAVA_HOME` to a 17 JDK |
| `'mvnw' is not recognized` | You're in PowerShell/CMD — use `.\mvnw.cmd` |
| Port 8080 already in use | An old run is still going. macOS/Linux: `lsof -i :8080` then `kill <pid>`. Windows: `netstat -ano \| findstr :8080` then `taskkill /PID <pid> /F` |
| Port 3306 or 8081 already in use | Another MySQL or phpMyAdmin is running — often a container from another project. `docker ps`, then `docker stop <name>` |
| `Cannot connect to the Docker daemon` | Docker Desktop isn't running. Start it, then retry |
| `Communications link failure` or `Could not obtain connection` on startup | `db` isn't running. `docker compose up -d db`, wait for **healthy**, then start the app |
| `Access denied for user` | The database login in `application.properties` doesn't match `docker-compose.yml`. Both should be `gallery` / `gallerypass` |
| Home page is empty, or test logins fail | The sample data didn't load — your volume was created before the dump was added. [Reset the database](#reset-the-database) |
| `Unable to find a @SpringBootConfiguration` | A test is missing `@SpringBootTest(classes = GalleryApplication.class)` |
| Tests pass locally but CI is red | Run `./mvnw verify` — it does more than `test` |
