# Simple-3D-Render-Engine

A simple 3d render engine written by pure Java codes

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

## Table of Contents

- [Background](#Background)
- [Exhibition](#Exhibition)
- [Install](#install)
- [Usage](#usage)
- [Structure](#Structure)
- [Ideas](#Ideas)
- [Maintainers](#Maintainers)
- [License](#license)

## Background

3D render engines that are nowdays used in games. I write a very simple 3d render engine demo so that it will really help me understand some fundamental principles which are also used for modern render engines.

Through making this render engine, I learn about orthographic projection, simple triangle rasterization, z-buffering, flat shading and subdividing a tetrahedron to a sphere.

## Exhibition

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Simple%20projection.gif"/> </div>

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Color%20projection.gif"/> </div>

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Shader%20projection.gif"/> </div>

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Final%20Sphere.gif"/> </div>

## Install

I Use Java language and IntelliJ IDEA (an IDE) under Windows 10 environment for this project.

[Java Download](https://www.java.com/en/download/)<br>
[IntelliJ IDEA Download](https://www.jetbrains.com/idea/download/#section=windows)<br>

## Usage

1. Download this repo, open(or zip and open) the **Simple-3D-Render-Engine** folder.

2. Use IntelliJ or any other appropriate IDE to open **Simple-3D-Render-Engine** folder as a project.

3. Find and run the `main` method under `DemoViewer.java` file to launch this simple 3d render program.

## Structure

The whole project in Unity contains two main folders, **Assets** folder and **Package** folder.

Under **Assets** folder, there are altogether **11** subfolders:

1. Animation folder: contains **8** animations we will use in this game including **fade in / out**, **platform movement** and so on.

2. Audio folder: contains **6** sound files we will use in this game including **Main Menu BGM**, **Click sound**, **Pause Game sound** and so on.

3. Fonts folder: contains **1** font we will use in this game(**Good Times** font).

4. Materials folder: contains **11** materials we will use in this game including **Floor material**, **Gem material**, **Finish Game material** and so on.

5. Objects folder: contains gem objects we will use in this game, there are also several sub folders(prefabs, gems and models) under this folder.

6. Prefabs folder: contains **3** prefabs we will use in this game including **DoubleBaseBlock** and so on.

7. Scences folder: contains **5** scenes, including **Main Menu Scene**, **Level 001**, **Credits** and so on.

8. Scripts folder: contains all **16** customized C# scripts we need in this game demo. 

9. SkyBox folder: contains skyboxes we need in this game demo. Download from the Unity Asset Store, you can download them [here](https://assetstore.unity.com/packages/2d/textures-materials/sky/skybox-volume-2-nebula-3392).

10. Textures folder: contains **8** textures, including **Floor texture**, **StartFinish texture** and so on.

11. Unity-chan! folder: contains the character we need in this game demo. Download from the Unity Asset Store, you can download them [here](https://assetstore.unity.com/packages/3d/characters/unity-chan-model-18705).

## Ideas

## Maintainers

[@Yunxiang-Li](https://github.com/Yunxiang-Li).

## License

[MIT license](https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/LICENSE)
