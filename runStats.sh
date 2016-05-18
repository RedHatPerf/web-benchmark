#!/bin/bash

MIN_RPS=5000
RPS_INCREMENT=5000
MAX_RPS=75000

RAMP_UP=150
DURATION=300
THINK_TIME=4
SIMULATION_CLASS="org.jboss.perf.HashingServletSimulations\$Get"

REQUESTS_PER_SECOND=$MIN_RPS
NO_REPORTS=TRUE

echo "Starting runs"
while [ $REQUESTS_PER_SECOND -lt $MAX_RPS ]
do
    echo "Running @: $REQUESTS_PER_SECOND"
    mvn -P client gatling:execute -Dtest.rps=$REQUESTS_PER_SECOND -Dtest.rampUp=$RAMP_UP -Dtest.duration=$DURATION -Dtest.pauseTime=$THINK_TIME -Dgatling.simulationClass=$SIMULATION_CLASS -Dgatling.noReports=$NO_REPORTS -Dgatling.outputName=$REQUESTS_PER_SECOND
    REQUESTS_PER_SECOND=$[$REQUESTS_PER_SECOND+$RPS_INCREMENT]
done
echo "done"
