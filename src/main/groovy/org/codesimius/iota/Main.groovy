package org.codesimius.iota

import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parse(new File(args[0]))
println new Hero(object.heroes[0])

Level.lvlPermutations.eachWithIndex { entry, i -> println "${i + 1}: $entry" }
