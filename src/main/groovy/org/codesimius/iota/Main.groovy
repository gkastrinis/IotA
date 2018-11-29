package org.codesimius.iota

//import groovy.json.JsonSlurper
//
//def jsonSlurper = new JsonSlurper()
//def object = jsonSlurper.parseText('{ "myList": [4, 8, 15, 16, 23, 42] }')
//println object.myList

def lvlConstraints = [
		[1], [1],
		[2], [2],
		[3],
		[3, 'U1'],
		[4, 'U1'], [4, 'U1'], [4, 'U1'],
		[4, 'U1', 'T1'], [4, 'U1', 'T1'],
		[4, 'U2', 'T1'], [4, 'U2', 'T1'], [4, 'U2', 'T1'],
		[4, 'U2', 'T2'], [4, 'U2', 'T2'], [4, 'U2', 'T2'],
		[4, 'U3', 'T2'], [4, 'U3', 'T2'],
		[4, 'U3', 'T3'], [4, 'U3', 'T3'], [4, 'U3', 'T3'], [4, 'U3', 'T3'], [4, 'U3', 'T3'],
		[4, 'U3', 'T4']
]

def lvlPerms = [[[1]]]
(1..24).each { lvl ->
	def res = []
	def constraints = lvlConstraints[lvl]
	def prevList = lvlPerms[lvl - 1]
	prevList.each { prev ->
		constraints.each { con ->
			if (!(con instanceof Integer) && (con.startsWith('U') || con.startsWith('T'))) {
				if (prev.find { it == con })
					return
				def i = prev.findIndexOf { !(it instanceof Integer) && it.startsWith(con[0]) }
				if (i >= 0) {
					def next = prev + []
					next[i] = con
					res << next.sort()
					return
				}
				res << (prev + [con]).sort()
				return
			}

			if (prev.size() < 3) res << (prev + [1])

			prev.toSet().each { elem ->
				if (elem instanceof Integer && elem < con) {
					def next = prev + []
					def i = next.findIndexOf { it == elem }
					next[i]++
					res << next.sort()
				}
			}
		}
	}
	lvlPerms[lvl] = res ? res.unique() : prevList
}

lvlPerms.eachWithIndex{ entry, i -> println "${i + 1}: $entry" }