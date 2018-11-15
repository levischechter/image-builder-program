# 3D-image-builder-program
a program that builds a 3D image using ray tracing algorithm

Hello everybody!
This project is a class-task in my seconed year at jct college.

NOTE:
The porpose of this task was to have an experiense in software engeneering, and not for a perfect image result!

I've build the project step-by-step with the lecture guide throughout the first semester in this year.
The project has been designed using a bunch of design patterns (including: Agile, TDD, Builder, Composite etc)

Here is some details on the project packages:

PRIMITIVES:
This package holds the code for primitives objects (objects with no shape),
That helps the program calculate the space.
Includes classes for:
1. coordinates
2. 2D points
3. 3D points (inherits from 2D points)
4. Vectors 
5. Rays (inherits from Vectors)
As the project progressed i added 2 more classes (Agile design) for the object features.
6. Colors (a different class from the java library for ease of use)
7. Material

GEOMETRIES:
This package holds the code for some shapes.
For each shape there is a spacial calculation for getting the intersection point with the ray.
Includes classes for:
1. geometry (abstract class)
2. radial geometry (abstract class, inherits from geometry)
3. plane (inherits from geometry)
4. triangle (inherits from plane)
5. rectangle (inherits from plane)
6. sphere (inherits from radial geometry)
7. geometries (a collection to hold all the geometries in an image)
8. intersectable (interface implemented by all the classes)

ELEMENTS:
This package holds the code for other elements in the image.
Includes classes for:
1. camera (for the point of view)
2. ambient light (the general color of the image)
3. light source (interface implemented by the next classes)
4. light (abstract class)
5. directional light (inherits from light)
6. point light (inherits from light)
7. spot light (inherits from point light)

SCENE:
This package holds the code for integrate and locate all the elements into a scene.
Includes class for:
1. scene (a collection of shapes lights and camera)

RENDERER:
This is the most important package in this program.
Here is where all the calculations performed and the image constructed.
includes classes for:
1. image builder (the "painter" of the image)
2. renderer (the calculator and compositor of the image).
! This renderer designed especially for a "glossy surface" result !

