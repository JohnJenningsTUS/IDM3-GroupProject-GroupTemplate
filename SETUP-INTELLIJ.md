# Setup — IntelliJ IDEA

Get the app running, tests passing, and Git working without leaving the IDE. Prefer the terminal? Use [SETUP-CLI.md](SETUP-CLI.md) instead.

## Before you start

1. **IntelliJ IDEA Ultimate**, free with your student email: https://www.jetbrains.com/community/education/
   Ultimate matters here — Spring Boot run configurations, the Thymeleaf editor and the Services/Docker tool windows are Ultimate-only. Community Edition works too; see the notes at the bottom.
2. **Docker Desktop** — runs MySQL and phpMyAdmin for you.
3. You do **not** need to install Java or Maven separately. IntelliJ downloads JDK 17 for you and the repo ships the Maven wrapper.

## 1. Clone the repo from IntelliJ

Welcome screen → **Get from VCS** → paste your team repo URL → **Clone**.

Already cloned? **File → Open**, and select the folder containing `pom.xml` — not a file inside it.

IntelliJ detects the Maven project and imports it. Wait for "Importing Maven projects" in the status bar to finish before touching anything — the first import downloads every dependency and takes a few minutes.

## 2. Set the JDK

**File → Project Structure → Project** → set **SDK** to a **Java 17** JDK. This is the version the Dockerfile and CI use, so matching it means your local build behaves like theirs.

No JDK 17 in the list? Open the dropdown → **Download JDK** → version 17, vendor Eclipse Temurin.

## 3. Switch to `develop`

Click the **branch widget** in the bottom-right status bar (or **Git → Branches**) → under *Remote*, pick `origin/develop` → **Checkout**.

## 4. Start MySQL and phpMyAdmin

Open `docker-compose.yml` and click the **green ▶ in the gutter** beside the `db:` service, then beside the `phpmyadmin:` service. Both appear in the **Services** tool window (Alt+8 / ⌘8). Wait until `db` shows **healthy**.

Don't start the `app:` service — you'll run the app from IntelliJ in the next step, and both use port 8080.

The first time `db` starts, it loads the sample data in `idm3project2024.sql`: users, projects and showcases. That only happens when the database volume is empty — see [Reset the database](#reset-the-database) below.

### phpMyAdmin

Open http://localhost:8081. It logs you straight in as the app's database user, so you can browse and edit the tables in `idm3project2024` — handy for checking what your code actually saved.

| Setting | Value |
| --- | --- |
| Host / port | `localhost:3306` |
| Database | `idm3project2024` |
| App user | `gallery` / `gallerypass` |
| Root user | `root` / `rootpass` |

Ultimate also has a built-in database client: **Database** tool window → **+ → Data Source → MySQL**, using the values above.

phpMyAdmin is for your laptop only. It's never deployed to test or prod.

## 5. Run the app

Open `src/main/java/idm3/project/gallery/application/GalleryApplication.java` and click the **green ▶** beside the class declaration → **Run 'GalleryApplication'**.

`dev` is the default profile, so it just works. Set it explicitly anyway on the run configuration IntelliJ just created — **Run → Edit Configurations** → select `GalleryApplication` → **Active profiles**: `dev` → **OK** — so that later, when you need `test` or `prod`, you already know where that field lives.

Open http://localhost:8080 — it redirects to the gallery home page, which lists the sample projects and showcases. The footer should read **TUS Gallery Build info: local dev**.

Stop with the red ■ in the **Run** tool window; restart with **Shift+F10**.

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

## 6. Run the tests

`db` must be running — the tests start the whole app, including its database connection.

Right-click `src/test/java` → **Run 'Tests in gallery'**. All green means your dev environment works.

While you're working, click the ▶ beside a single test class to run just that one — much faster than the full suite.

> **Checkpoint ✅** App running locally, test logins working, tests passing.

## Reset the database

To throw away your local data and reload the sample data, use the **Terminal** tool window (Alt+F12):

```bash
docker compose down -v
docker compose up -d db phpmyadmin
```

`-v` deletes the database volume — everything you've added locally is gone. Do this when your data gets into a mess, or after a teammate changes `idm3project2024.sql`.

## Day-to-day Git in IntelliJ

| Task | IntelliJ | Shortcut |
| --- | --- | --- |
| Get latest `develop` | **Git → Update Project** | Ctrl+T / ⌘T |
| New branch | Branch widget → **New Branch from 'develop'** | — |
| See what you changed | **Commit** tool window | Alt+0 / ⌘0 |
| Commit | Commit window → tick files, write message → **Commit** | Ctrl+K / ⌘K |
| Push | **Git → Push** | Ctrl+Shift+K / ⌘⇧K |
| Switch branch | Branch widget → branch → **Checkout** | — |
| Review history | **Git** tool window → **Log** tab | Alt+9 / ⌘9 |

Name branches in the New Branch dialog exactly as the rules require: `feature/<issue-number>-<short-name>`.

**Reviewing a teammate's PR**: enable the bundled **GitHub** plugin, then **Git → GitHub → View Pull Requests**. You can read the diff, comment on lines, and check out their branch to run it — which the review rules require you to actually do.

## Running the CI build locally

CI runs Maven, not IntelliJ, so this is the command that decides whether your PR goes green. Use the **Terminal** tool window (Alt+F12):

```bash
./mvnw verify
```

CI starts its own empty MySQL for this, so don't write tests that rely on the sample data. If it passes in IntelliJ but fails in CI, run that first — the difference is nearly always the answer. Full command reference in [SETUP-CLI.md](SETUP-CLI.md).

## Community Edition notes

Community Edition has no Spring Boot run configuration, so:

- Run `GalleryApplication` as an ordinary Java application — the gutter ▶ still works.
- Set the profile by hand: **Edit Configurations → Modify options → Add VM options** → `-Dspring.profiles.active=dev`.
- Docker support isn't bundled. Either install the **Docker** plugin from Settings → Plugins, or start MySQL and phpMyAdmin from the terminal with `docker compose up -d db phpmyadmin`.
- There's no built-in database client — use phpMyAdmin at http://localhost:8081.
- Thymeleaf templates get no autocomplete or navigation. They still render fine.

Everything else here is identical.

## Troubleshooting

| Symptom | Fix |
| --- | --- |
| Red squiggles everywhere, imports unresolved | Maven import didn't finish. **Maven** tool window → **Reload All Maven Projects** |
| `Invalid source release: 17` | Wrong JDK. **Project Structure → Project → SDK** → Java 17 |
| Port 8080 already in use | An old run is still going, or the `app:` Docker service is running. Red ■ in the **Run** window, or stop it in **Services** |
| Port 3306 or 8081 already in use | Another MySQL or phpMyAdmin is running — often a container from another project. Stop it in **Services** |
| `Cannot connect to the Docker daemon` | Docker Desktop isn't running. Start it, then retry |
| `Communications link failure` or `Could not obtain connection` on startup | `db` isn't running. Start it from `docker-compose.yml`, wait for **healthy**, then run the app |
| `Access denied for user` | The database login in `application.properties` doesn't match `docker-compose.yml`. Both should be `gallery` / `gallerypass` |
| Home page is empty, or test logins fail | The sample data didn't load — your volume was created before the dump was added. [Reset the database](#reset-the-database) |
| `Unable to find a @SpringBootConfiguration` | A test is missing `@SpringBootTest(classes = GalleryApplication.class)` |
| Changes to a template don't show | Rebuild with Ctrl+F9 / ⌘F9, then refresh the browser |
