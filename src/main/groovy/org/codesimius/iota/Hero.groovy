package org.codesimius.iota

import groovy.transform.ToString

@ToString(includeNames=true)
class Hero {
	String name
	String mainAttr
	int baseStr
	float gainStr
	int baseAgi
	float gainAgi
	int baseInt
	float gainInt

	int baseHP
	float regenHP

	float mgRes
	int baseMP
	float regenMP
	float mgDmg

	float armor
	float atckPS
	int dmgMin
	int dmgMax
	int atckRange
	int projSpeed
	float atckPoint
	float atckBackSwg
	float BAT

	int speed
	float speedAmp
	float turnRate
	int visionDay
	int visionNight
}
