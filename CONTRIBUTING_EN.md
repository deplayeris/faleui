# Contribution Guidelines and Standards

Welcome to contribute to the faleUI project! To maintain code quality and project consistency, please carefully read the following guidelines.

## 📁 Documentation Storage Locations

- **External contribution documentation** (such as this file): Place in the source code root directory
- **Development documentation**: Please write library guide documentation in the `docs/` folder, internal documentation in `devdocs/`.
  - API documentation
  - Development guides
  - Technical design documents
  - Internal implementation details, etc.

## 🔧 Git Commit Standards

### Commit Message Format

```
<type>(<scope>): <short description>

[Optional detailed description]

[Optional related information]
```

### Type Descriptions

| Type | Description | Example |
|------|-------------|---------|
| `feat` | New feature | `feat(gui): add button component` |
| `fix` | Bug fix | `fix(core): fix rendering exception` |
| `docs` | Documentation update | `docs(readme): update installation guide` |
| `style` | Code formatting adjustments (no functional impact) | `style(format): unify code indentation` |
| `refactor` | Code refactoring | `refactor(ui): optimize component architecture` |
| `perf` | Performance optimization | `perf(render): improve rendering performance` |
| `test` | Test-related | `test(button): add unit tests` |
| `chore` | Build process or auxiliary tool changes | `chore(gradle): update dependency versions` |
| `ci` | CI configuration related | `ci(workflow): modify build workflow` |

### Scope Descriptions

Common scopes include:
- `core` - Core functionality
- `gui` - GUI components
- `render` - Rendering system
- `event` - Event system
- `util` - Utility classes
- `api` - API interfaces
- `config` - Configuration system
- `i18n` - Internationalization
- `compat` - Compatibility

### Commit Examples

**Good Examples:**
```
feat(gui): add draggable window component

- Implement basic window drag functionality
- Support boundary detection and constraints
- Add visual feedback effects during dragging

Fixes #123
```

**Poor Examples:**
```
update code
fix bug
add new feature
```

## 🔄 Pull Request Standards

### PR Title Format

```
<type>: <short description> (#PR number)

Example: feat: add configuration menu interface (#45)
```

### PR Description Template

Use the following template when creating a PR:

```markdown
## Change Description
<!-- Please describe the content and purpose of this change in detail -->

## Related Issues
<!-- If there are related issues, please fill them in -->
- Closes #issue_number
- Related to #issue_number

## Change Type
<!-- Please check the applicable change types -->
- [ ] New Feature (feat)
- [ ] Bug Fix (fix)
- [ ] Documentation Update (docs)
- [ ] Code Refactoring (refactor)
- [ ] Performance Optimization (perf)
- [ ] Testing (test)
- [ ] Other (please specify)

## Checklist
<!-- Please confirm the following items -->
- [ ] Code complies with project coding standards
- [ ] Added necessary tests
- [ ] Updated relevant documentation
- [ ] Passed all tests
- [ ] No new warnings or errors introduced

## Testing Instructions
<!-- Please describe how to test these changes -->

## Screenshots (if applicable)
<!-- If this is a UI-related change, please provide screenshots -->
```

## 🏷️ Version Tag Standards

### Version Number Format

Using semantic versioning: `MAJOR.MINOR.PATCH[-prerelease][+build-metadata]`

Current project version format: `0.1d+26.1s2`
- `0.1d` - Main version identifier
- `26.1s2` - Corresponding Minecraft snapshot version

### Tag Naming Rules

```
v<version-number>

Examples:
v0.1.0
v0.1.1-beta.1
v1.0.0+26.1s2
```

### Tag Release Timing

1. **Official Release**: After completing feature development and testing
2. **Pre-release**: Versions that need testing but are not yet stable
3. **Milestone**: Important feature completion milestones

## 🚀 Release Standards

### Release Title Format

```
faleUI v<version-number> - <short description>

Example: caleUI v0.1.0 - First stable release
```

### Release Content Template

```markdown
## 🎉 New Version Release

**faleUI v0.1.0** is now released!

### 📋 Update Contents

#### New Features 🆕
- [Feature 1 description]
- [Feature 2 description]

#### Bug Fixes 🐛
- [Fix 1 description]
- [Fix 2 description]

#### Performance Optimizations ⚡
- [Optimization 1 description]

### 📦 Download Links

- [Download link 1]
- [Download link 2]

### 📖 Documentation

- [Complete documentation](docs/)
- [API reference](docs/api-reference.md)
- [User guide](docs/getting-started.md)

### ⚠️ Important Notes

[Important compatibility notes or other considerations]

### 🙏 Acknowledgements

Thank you to all contributors for their efforts!

@contributor1 @contributor2
```

## 🛠️ Development Environment Setup

### Required Tools

- Java 25 JDK
- IntelliJ IDEA (recommended)
- Git

### Project Configuration

1. Clone the repository
```bash
git clone https://github.com/deplayeris/faleui.git
cd faleui
```

2. Import into IDE
   - Open the project with IntelliJ IDEA
   - Wait for Gradle synchronization to complete

3. Build the project
```bash
# Windows
.\gradlew build

# Linux/macOS
./gradlew build
```

## ✅ Code Quality Requirements

### Coding Standards

- Follow Java 25 language features
- Use meaningful variable and method names
- Add JavaDoc comments to all public methods
- Maintain consistent code style

### Testing Requirements

- New features must include unit tests
- Bug fixes need regression tests
- Ensure all tests pass before submission

### Documentation Requirements

- Public APIs must have complete documentation
- Major changes need README updates
- Complex features require detailed explanations in the docs directory

## 🤝 Community Code of Conduct

- Maintain friendly and professional attitudes
- Respect different viewpoints and technical choices
- Provide feedback constructively
- Help novice developers

## ❓ Getting Help

If you have questions, please:
1. Check existing documentation
2. Search for related issues in Issues
3. Create a new Issue for discussion

---

Thank you for your contributions! 🎉