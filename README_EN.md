# faleUI - Minecraft UI Library Mod

[![MIT License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat&labelColor=444444)](https://opensource.org/licenses/MIT)
[![Fabric](https://img.shields.io/badge/Fabric-0.18.4+-orange?style=flat&labelColor=444444&logo=curseforge&logoColor=white)](https://fabricmc.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-green?style=flat&labelColor=444444&logo=minecraft&logoColor=white)](https://www.minecraft.net/)
[![Java](https://img.shields.io/badge/Java-25-red?style=flat&labelColor=444444&logo=openjdk&logoColor=white)](https://adoptium.net/)
<br>A Minecraft UI library mod based on Fabric, providing developers with powerful interface creation tools.

## 🎯 Features
This mod does not have any particularly outstanding features; it mainly simplifies some of the more tedious UI development tasks through encapsulation. However, it also includes certain optimizations and advanced functionalities, aiming to make UI development as straightforward as possible. Users can create their own UI textures and styles, but if they choose not to design them and only create the UI, the default Minecraft style will be used.

## 📦 Installation Guide

### System Requirements
- **Minecraft Version**: 1.21.11
- **Mod Loader**: Fabric compatible with this Minecraft version, requires fabric-api
- **Java Version**: JDK 25
- Recommended 1600MB total game runtime space

### Build Method

```bash
# Windows
.\gradlew build

# Linux/macOS
./gradlew build
```

After compilation, you can find the generated jar file in the `build/libs/` directory.

### Installation Steps
1. Download and install [Fabric](https://fabricmc.net/)
2. Place the compiled jar file in the `.minecraft/mods` folder
3. Launch the game to use it

## 📚 Documentation

Developers can view detailed development documentation and API specifications in the [`docs`](docs/) folder.

## 🤝 Contribution Guidelines

We welcome all forms of contributions! Please check the detailed [contribution standards](CONTRIBUTING_EN.md) for complete submission and PR guidelines.

### Quick Start

1. **Fork the project** to your GitHub account
2. **Clone locally**:
   ```bash
   git clone https://github.com/deplayeris/faleui.git
   cd faleui
   ```
3. **Create a feature branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. **Develop** following the [contribution standards](CONTRIBUTING_EN.md)
5. **Commit changes** and push
6. **Create Pull Request**

### Documentation Guidelines

- **External contribution documentation**: Located in the source code root directory (such as this README, CONTRIBUTING_EN.md, etc.)
- **Internal development documentation**: Please write in the [`docs/`](docs/) folder

### Code Standards
- Use Java 25 syntax features
- Ensure every object and function/method has corresponding IDE intelligent suggestions (primarily for IDEA)

## 📄 License and Compliance

This project uses the **MIT License**. See the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 Deplayer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

Additionally, this mod strictly complies with the [Minecraft End User License Agreement (EULA)](https://account.mojang.com/documents/minecraft_eula).

## 📞 Contact Information

- **Author**: Deplayer
- **Email**: deplayer515@hotmail.com
- **GitHub Issues**: [Submit issue reports](https://github.com/deplayeris/faleui/issues)

## 🙏 Acknowledgements

Thank you to all developers, the Fabric development team, community tutorial experts, and users who have contributed to this project!

---