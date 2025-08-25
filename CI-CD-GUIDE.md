# CI/CD Pipeline Guide for Developers

## Overview
This document explains the GitHub Actions CI/CD pipeline implemented for the Uno Game Engine project. The pipeline automates building, testing, and releasing the Java application.

## Pipeline Files

### 1. Main Pipeline: `.github/workflows/ci-cd.yml`
Handles building, testing, and releasing the application.

### 2. Quality Pipeline: `.github/workflows/code-quality.yml`
Ensures code quality and style compliance.

## How the CI/CD Pipeline Works

### Trigger Events
The pipeline runs automatically when:
- **Code is pushed** to `main` or `develop` branches
- **Pull request is created** targeting the `main` branch

### Pipeline Flow

```
Code Push → Build Job → Release Job (main branch only)
     ↓
Quality Check Job (parallel)
```

## Detailed Breakdown: ci-cd.yml

### Job 1: Build (Always Runs)

#### Step 1: Environment Setup
```yaml
runs-on: ubuntu-latest
uses: actions/checkout@v4
uses: actions/setup-java@v4
```
**What it does:**
- Spins up Ubuntu virtual machine
- Downloads your code from GitHub
- Installs Java 21

#### Step 2: Compilation
```yaml
run: mkdir -p bin
run: javac -d bin src/*.java
```
**What it does:**
- Creates `bin/` directory for compiled files
- Compiles all `.java` files from `src/` folder
- Stores `.class` files in `bin/` directory

#### Step 3: Automated Testing
```yaml
run: |
  cd bin
  echo -e "2\nPlayer1\nPlayer2" | java GameDriver
```
**What it does:**
- Runs the application with test input
- Simulates 2 players: "Player1" and "Player2"
- Verifies the application starts and runs correctly

#### Step 4: Package Creation
```yaml
run: |
  cd bin
  jar cfe ../uno-game-engine.jar GameDriver *.class
```
**What it does:**
- Creates executable JAR file
- Sets `GameDriver` as the main class
- Packages all compiled classes

#### Step 5: Artifact Storage
```yaml
uses: actions/upload-artifact@v4
with:
  name: uno-game-engine
  path: uno-game-engine.jar
```
**What it does:**
- Stores JAR file for download
- Makes it available to other jobs
- Allows manual download from GitHub Actions page

### Job 2: Release (Main Branch Only)

#### Conditions
```yaml
needs: build
if: github.ref == 'refs/heads/main'
```
**Requirements:**
- Build job must complete successfully
- Only runs for pushes to `main` branch

#### Step 1: Get Build Artifact
```yaml
uses: actions/download-artifact@v4
with:
  name: uno-game-engine
```
**What it does:**
- Downloads JAR file created by build job

#### Step 2: Create GitHub Release
```yaml
uses: actions/create-release@v1
with:
  tag_name: v${{ github.run_number }}
  release_name: Release v${{ github.run_number }}
```
**What it does:**
- Creates new release on GitHub
- Auto-generates version number (v1, v2, v3, etc.)
- Uses GitHub's run number for unique versioning

#### Step 3: Attach JAR to Release
```yaml
uses: actions/upload-release-asset@v1
with:
  asset_path: ./uno-game-engine.jar
  asset_name: uno-game-engine.jar
```
**What it does:**
- Attaches JAR file to the GitHub release
- Makes it downloadable by users
- Provides distribution mechanism

## Code Quality Pipeline: code-quality.yml

### Purpose
Ensures all code meets quality standards before merging.

### What it checks:
- **Compilation**: Code must compile without errors
- **Style**: Follows Google Java Style Guide
- **Standards**: Consistent formatting and naming

## Developer Workflow

### For Feature Development:
1. **Create branch** from `develop`
2. **Make changes** to code
3. **Push to branch** → Pipeline runs build + quality checks
4. **Create PR to develop** → Pipeline validates changes
5. **Merge to develop** → Pipeline runs full validation

### For Releases:
1. **Merge develop to main** → Pipeline creates automatic release
2. **Download JAR** from GitHub Releases page
3. **Distribute** to users

## Pipeline Benefits

### Automation
- **No manual builds** - Everything automated
- **No manual testing** - Basic tests run automatically  
- **No manual releases** - GitHub releases created automatically

### Quality Assurance
- **Code must compile** - Broken code can't be merged
- **Style enforcement** - Consistent code formatting
- **Functional testing** - Basic application testing

### Distribution
- **Automatic versioning** - No manual version management
- **Easy downloads** - JAR files available on GitHub
- **Release history** - Track all versions

## Troubleshooting

### Build Failures
- Check compilation errors in Actions log
- Verify all Java files are syntactically correct
- Ensure no missing dependencies

### Test Failures
- Application must accept input and run without crashing
- Check GameDriver main method functionality






