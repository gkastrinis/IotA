package org.codesimius.iota

class Level {
	static def lvlConstraints = [
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
	static def lvlCombinations = [[[1]]]
	static def lvlPermutations = []

	static {
		(1..24).each { lvl ->
			def currCombinations = []
			def addToCurr = { currCombinations << it.sort() }

			lvlCombinations[lvl - 1].each { prev ->
				lvlConstraints[lvl].each { con ->
					if (con instanceof String && (con.startsWith('U') || con.startsWith('T'))) {
						if (prev.find { it == con }) return

						def i = prev.findIndexOf { it instanceof String && it.startsWith(con[0]) }
						if (i >= 0) {
							def clone = prev + []
							clone[i] = con
							addToCurr clone
							return
						}
						addToCurr(prev + [con])
						return
					}

					if (prev.size() < 3) addToCurr(prev + [1])

					prev.toSet().each { elem ->
						if (elem instanceof Integer && elem < con) {
							def clone = prev + []
							def i = clone.findIndexOf { it == elem }
							clone[i]++
							addToCurr clone
						}
					}
				}
			}
			lvlCombinations[lvl] = currCombinations ? currCombinations.unique() : lvlCombinations[lvl - 1]
		}

		lvlPermutations = lvlCombinations.collect { combinations ->
			combinations.collectMany { combination ->
				def numbers = [], extra = []
				combination.each { (it instanceof Integer ? numbers : extra) << it }
				def numbersExt = numbers + (0..<(3 - numbers.size())).collect { 0 }
				numbersExt.permutations().collect { it + extra }
			}
		}
	}
}
