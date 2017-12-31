# PitGraph

This project is an excersize project to the specifications listed [here](https://github.com/HendrixString/sfly-quest)
I had a lot of fun doing this excersise, so I added many bells and whistles that were not part of the specification.

## Concept
The guidence to this excersize suggested to implement Pit as a ViewGroup, anf the points as View, and let Pit hold them.
My idea for the implementation was to derive PitView directly from View and draw the points on the canvas.
The points than a lightweit logical object held in a logical container, that provides the world information for painting.

## Architecture
### Model
#### PitPOint
Class derived from Point, and holds the values in terms of the display.
It also hold reference to the Zero point, so it can calculate its value relative to the center of the axces (0,0)
This calss also implement Comparable, based on the 'X' value, this is so it can easily be sorted.

#### PointCollection
Class to hold the logical data for the display.
it contains a list of points and a point that represent the center of the Axces (0,0)

### View
#### PitView
This is a View Class, responsible for drawing the Pit line, and manages the touch events.
it references a PointCollection object that holds the points for drawing
Drawing is done directly on the canvas.

![Architecture](/images/PitArchitecture.png) 

