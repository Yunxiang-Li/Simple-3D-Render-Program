# Simple-3D-Render-Engine

A simple 3d render engine written by pure Java codes

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

## Table of Contents

- [Background](#Background)
- [Exhibition](#Exhibition)
- [Install](#install)
- [Ideas](#Ideas)
- [Structure](#Structure)
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

## Ideas

1. Download this repo, open(or zip and open) the **Simple-3D-Render-Engine** folder.

2. Use IntelliJ or any other appropriate IDE to open **Simple-3D-Render-Engine** folder as a project.

3. Find and run the `main` method under `DemoViewer.java` file to launch this simple 3d render program.

## Structure

The whole project in Unity contains two main folders, **Assets** folder and **Package** folder.

Under **src** folder, there are altogether **4** `java` classes:

1. `Vertex` class represents a position in 3d space.

2. `Triangle` class represents a triangle binding three vertices  together and stores its color.

3. `Matrix3` class represents a 3D Matrix which will help us do matrix-matrix and vector-matrix multiplications.

4. `DemoViewer` class is our main entrance of the render program, most of processes are made here.

## Maintainers

[@Yunxiang-Li](https://github.com/Yunxiang-Li).

## License

[MIT license](https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/LICENSE)
