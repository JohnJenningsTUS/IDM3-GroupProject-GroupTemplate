# TUS Gallery — Team Starter Repo



## Your test environment

> **Test server URL**: `http://REPLACE-ME:8080` *(your instructor sets this per team)*

## Choose your setup guide

Both guides get you to the same place: the app running locally, tests passing, ready to open your first PR. Pick one and work through it.

| Guide | Use this if |
| --- | --- |
| **[SETUP-INTELLIJ.md](SETUP-INTELLIJ.md)** | You want the IDE to handle building, running, debugging and Git. Recommended if you're new to the command line. |
| **[SETUP-CLI.md](SETUP-CLI.md)** | You prefer the terminal and your own editor, or you're working over SSH / on a low-spec machine. |

Whichever you pick, **CI runs the command-line build** (`./mvnw verify`) — so it's worth being able to run that yourself when a PR goes red. The CLI guide is the reference for that.

Agree as a team which guide you're all using. Mixed setups are fine, but same-tool teams help each other faster.

## What's here

| Path | What it is |
| --- | --- |
| `src/main/java/ie/tus/gallery` | Spring Boot app: public pages + form login (in-memory users for now) |
| `src/main/resources/templates` | `home`, `team`, `login`, `dashboard` Thymeleaf views |
| `src/test/java` | MockMvc tests — CI runs these on every PR |
| `.github/workflows/ci.yml` | Builds + tests every PR |
| `.github/workflows/deploy-test.yml` | Auto-deploys `develop` to the test server |
| `docker-compose.yml` | Local dev: app + MySQL |
| `application-{dev,test,prod}.properties` | One config per environment |

### Test logins

Sign in at http://localhost:8080/MainGallery/Login with any of these accounts from the sample data:

| Role | Username | Password |
| --- | --- | --- |
| Student | `TRYAN` | `pass123` |
| Student | `MSith` | `123pass` |
| Administrator | `tsmith` | `pass123` |
| Administrator | `EBourke` | `Bourke123` |
| Employer (viewer) | `PCbus` | `PC123` |

`.idea/` and `.vscode/` are already in `.gitignore` — never commit editor settings.

## Rules (enforced by branch protection)

1. Never push to `develop` or `main` — branch, then PR.
2. PRs need green CI **and** one teammate approval.
3. Never merge on red; never delete a test to make CI pass.
4. Branch names: `feature/<issue-number>-<short-name>`.

