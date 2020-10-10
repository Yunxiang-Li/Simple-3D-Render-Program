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

1. First in main class `demoviewer`, using **Java AWT(Abstract Window Toolkit)** and **Java Swing** to create a GUI wrapper like this:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/GUI%20wrapper.png"/> </div>

2. Then I add two essential classes, `Vertex` and `Triangle`. Vertex is a simple structure which stores three coordinates (X, Y and Z), and triangle binds together three vertices and stores its color. 

3. After that, I create a tetrahedron by creating a list of 4 triangles, display its orthographic projection in the screen and add rotation to two scroll bars. Here I assume that X coordinate means movement in left-right direction, Y means movement up-down on screen, and Z will be depth (so Z axis is perpendicular to our screen). Positive Z will mean "towards the observer". In order to add rotation, I use matrix-matrix multiplication in three planes:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/matrix%20for%20rotation.JPG"/> </div>

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
