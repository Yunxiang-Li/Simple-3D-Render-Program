# Simple-3D-Render-Program

A simple 3d render program written by pure Java codes

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

3D render engines that are nowdays used in games. I wrote a very simple 3d render program so that it would help me understand some fundamental principles which were also used for modern render engines.

Through making this render program, I learned about orthographic projection, simple triangle rasterization, z-buffering, flat shading and subdividing a tetrahedron to a sphere.

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

Then we have:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Simple%20projection.gif"/> </div>

4. Now we need to start filling up those triangles with colors. To do this we need to rasterize the triangle - convert it to list of pixels on screen that it occupies.
I choose rasterization via barycentric coordinates since it is easier to implement. The idea is to compute barycentric coordinate for each pixel that could possibly lie inside the triangle and discard those that are outside.

However, the reasterization order will affect which triangle we will see in the end(sometimes I can only see the triangle below but cannot see the one above), This is because the triangle below may be reasterizated later. To fix this I use z-buffer (or depth buffer). The idea is to build an intermediate array during rasterization that will store each position's last time seen depth. When rasterizing triangles, I will check whether each position's current pixel depth is larger than previously seen, and only color the pixel if it is deeper(has larger depth which means closer to users). And after this, we have:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Color%20projection.gif"/> </div>

5. Then I try to add light effect. In computer graphics, we can achieve similar effect by using "shading" - altering the color of the surface based on its angle and distance to lights.

Here I will use simplest shading - flat shading. It takes into account only the angle between surface normal and direction of the light source. We just need to find cosine of angle between those two vectors and multiply the color by the resulting value. Such approach is very simple and cheap. First we need to compute the normal vector of each triangle. Then we need to calculate cosine between triangle normal and light direction. For simplicity, I will use directional light here(light is positioned directly behind the camera at some infinite distance).
After this, we have:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Shader%20projection.gif"/> </div>

6. Finally, in order to change the tetrahedron to a sphere, we need to repeatedly subdivide each triangle into four smaller ones and keep inflating(here I choose to inflat 4 times for each triangle since my computer will become lag D:). Here is the final result:

<div align="center"> <img src="https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/Screenshots%20and%20GIFs/Final%20Sphere.gif"/> </div>

## Structure

Under **src** folder, there are altogether **4** `java` classes:

1. `Vertex` class represents a position in 3d space.

2. `Triangle` class represents a triangle binding three vertices  together and stores its color.

3. `Matrix3` class represents a 3D Matrix which will help us do matrix-matrix and vector-matrix multiplications.

4. `DemoViewer` class is our main entrance of the render program, most of processes are made here.

## Maintainers

[@Yunxiang-Li](https://github.com/Yunxiang-Li).

## License

[MIT license](https://github.com/Yunxiang-Li/Simple-3D-Render-Engine/blob/main/LICENSE)
