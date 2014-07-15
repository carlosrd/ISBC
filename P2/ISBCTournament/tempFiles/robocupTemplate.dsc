//======
// MAX TIME STEP
//======
// 
// maxtimestep milliseconds
//
// maxtimestep statements set the maximum time (in milliseconds) that can
// transpire between discrete simulation steps.  This will keep the simulation
// from getting jumpy on slow machines, or when/if your process gets
// swapped out.

maxtimestep $timestep$

//======
// TIMEOUT
//======
//
// timeout time
//
// The timeout statement indicates when the simulation will terminate in
// milliseconds.  The program automatically terminates when this time
// is reached.  If no timeout statement is given, the default is no
// termination.
//
timeout $timeout$ // ten seconds

//======
// TIME
//======
//
// time accel_rate
//
// The time statement sets the rate at which simulation time progresses
// with respect to real time.  "time 0.5" will cause the simulation to
// run at half speed, "time 1.0" will cause it to run at real time,
// while "time 4.0" will run at 4 times normal speed.  Be careful
// about too high of a value though because the simulation will
// lose fidelity.  In fact, for slow computers, values less than 1.0
// may be necessary. 

time $accel_rate$

//======
// LOGTIME
//====== 
// 
// logtime number (milliseconds)
// 
logtime $logtime$

//======
// LOGFILENAME
//====== 
// 
// logfilename filename (saved in logfiles directory)
// 
logfilename $filename$

//======
// The ball
//======
object EDU.gatech.cc.is.simulation.GolfBallNoiseSim 0 0 0 0.02 
	xffffff x000000 3
	
//======
// ROBOTS
//======
//
// robot robottype controlsystem x y theta forecolor backcolor 
//		visionclass
//
// Robot statements cause a robot with a control system to be instantiated
// in the simulation.  Be sure to include the full class name for the
// abstract robot type and your control system.  The y and theta
// parameters are actually ignored for soccer robots because they
// have pre-assigned initial locations.  The x parameter indicates
// whether the robot is on the east (positive) or west (negative).
// You can used different colors to tell your team or individual
// robots apart from one another.  The robots are assigned their player
// numbers according to the order in which they are listed here.
//======WEST TEAM======
westname $westname$
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $westrobot$
//------------your control system name goes here ^^^^^^^^
	-1.2  0    0 x3399ff x444444 1
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $westrobot$
//------------your control system name goes here ^^^^^^^^
	-.5   0    0 x3399ff x444444 1
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $westrobot$
//------------your control system name goes here ^^^^^^^^
	-.15  .5   0 x3399ff x444444 1
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $westrobot$
//------------your control system name goes here ^^^^^^^^
	-.15  0    0 x3399ff x444444 1
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $westrobot$
//------------your control system name goes here ^^^^^^^^
	-.15  -.5  0 x3399ff x444444 1

//======EAST TEAM======
eastname $eastname$
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $eastrobot$
	 1.2  0    0 xff3333 xffff66 2
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $eastrobot$
	 .5   0    0 xff3333 xffff66 2
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $eastrobot$
	 .15  .5   0 xff3333 xffff66 2
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $eastrobot$
	 .15  0    0 xff3333 xffff66 2
robot EDU.gatech.cc.is.abstractrobot.SocSmallSim $eastrobot$
	 .15  -.5  0 xff3333 xffff66 2

