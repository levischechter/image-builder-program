# 3D-image-builder-program
a program that builds a 3D image using ray tracing algorithm

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
As the project progressed i added 2 more classes (Agile design) for the object features.
6. Colors (a different class from the java library for ease of use)
7. Material

GEOMETRIES:
this package holds the code for some shapes.
includes classes for:
1. geometry (abstract class)
2. radial geometry (abstract class, inherits from geometry)
3. plane (inherits from geometry)
4. triangle (inherits from plane)
5. rectangle (inherits from plane)
6. sphere (inherits from radial geometry)
7. geometries (a collection to hold all the geometries in an image)
8. intersectable (interface implemented by all the classes)

ELEMENTS:
this package holds the code for other elements in the image.
includes classes for:
1. camera (for the point of view)
2. ambient light (the general color of the image)
3. light source (interface implemented by the next classes)
4. light (abstract class)
5. directional light (inherits from light)
6. point light (inherits from light)
7. spot light (inherits from point light)

SCENE:
this package holds the code for integrate and locate all the elements into a scene.
includes class for:
1. scene (a collection of shapes lights and camera)

RENDERER:
this is the most important package in this program.
here is where all the calculations performed and the image constructed.
includes classes for:
1. image builder (the "painter" of the image)
2. renderer (the calculator and compositor of the image).
! This renderer designed especially for a "glossy surface" result !

