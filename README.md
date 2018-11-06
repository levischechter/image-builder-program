# 3D-image-builder-program
a program that b ilds an image using ray tracing algorithm

hello everybody!
this project is a class-task in my seconed year at jct college.

the porpose of this task was to have an experiense in software engeneering.
i've build the project step-by-step with the lecture guide throughout the first semester in this year.
the project has been designed using a bunch of design patterns (including: Agile, TDD, Builder, Composite etc)

here is some details on the project packages:

PRIMITIVES:
this package holds the code for primitives objects (objects with no shape),
that helps the program calculate the space.
includes classes for:
1. coordinates
2. 2D points
3. 3D points (inherits from 2D points)
4. Vectors 
5. Rays (inherits from Vectors)
Asthe project progressed i added 2 more classes (Agile design) for the object features.
6. Colors (a different class from the java library for ease of use)
7. Material

GEOMETRIES:
as the package name, this package holds the code for some shapes.
includes classes for:
1. geometry (abstract class)
2. radial geometry (abstract class, inherits from geometry)
3. plane (inherits from geometry)
4. triangle (inherits from plane)
5. rectangle (inherits from plane)
6. sphere (inherits from radial geometry)
7. intersectable (interface implemented by all the classes)

