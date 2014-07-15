// This description file specifies the environment for a RoboCup 
// soccer game simulation in the JavaBotSim simulator.

//======
// SIMULATION BOUNDARY
//======
//
// bounds left right bottom top
//
// bounds statements set the bounds of the visible "playing field" in
// meters for a simulation.   If the aspect ratio of the bounds are not 
// the same as the graphical area set aside by the simulation, then 
// the robots may wander off the screen.  This will be fixed (eventually!)
// These are the bounds of a RoboCup field "base."  The actual field walls
// are a little bit smaller, but we reserve all this space so the complete
// field can be drawn

bounds -1.47 1.47 -.8625 .8625


//======
// SEED
//====== 
// 
// seed number
// 
// The seed statement sets the random number seed.  The default is
// -1

seed 3

//======
// BACKGROUND COLOR
//======
//
// background color
//
// A background statement sets the background color for the simulation.
// The color must be given in hex format as "xRRGGBB" where RR indicates
// the red component (00 for none, FF for full), GG is the green component,
// and BB is the blue.  For soccer, we use dark green.

background x009000


//======
// OBJECTS
//======
//
// object objecttype x y theta forecolor backcolor visionclass
//
// Object statements cause a simulated object to be instantiated
// in the simulation.  Be sure to include the full class name for the
// object.  The x y and theta parameters set the initial position of 
// object in the field.  Forecolor and backcolor are the foreground
// and background colors of the object as drawn. The visionclass
// parameter is used to put each kind of object into it's own perceptual
// class.  That way when the simulated sensors of robots look for things
// they can be sorted by this identifier.
//

// SocFieldSmallSim is a special object that draws the field.  It
// has no physical interactions with the robots or the ball.  It
// is instantiated first so it will be drawn first and not
// on top of the robots or the ball.
object EDU.gatech.cc.is.simulation.SocFieldSmallSim 0 0 0 0 x009000 x000000 0

// To simulate the corner panels, we use invisible 1 meter diameter 
// obstacles whose centers are outside the playing field
object EDU.gatech.cc.is.simulation.ObstacleInvisibleSim 2.047 1.4396 0 1.0 
	x000000 x000000 0
object EDU.gatech.cc.is.simulation.ObstacleInvisibleSim -2.047 1.4396 0 1.0
	x000000 x000000 0
object EDU.gatech.cc.is.simulation.ObstacleInvisibleSim 2.047 -1.4396 0 1.0
	x000000 x000000 0
object EDU.gatech.cc.is.simulation.ObstacleInvisibleSim -2.047 -1.4396 0 1.0 
	x000000 x000000 0