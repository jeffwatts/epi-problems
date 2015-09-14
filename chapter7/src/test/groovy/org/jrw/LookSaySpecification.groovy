package org.jrw

import spock.lang.Specification

class LookSaySpecification extends Specification {
  def 'with a non-negative integer argument'() {
    expect:
      LookSay.atIndex(index) == expectedResult

    where:
      index || expectedResult
      0     || 1
      1     || 11
      2     || 21
      3     || 1211
      4     || 111221
      5     || 312211
      6     || 13112221
  }

  def 'with a negative integer argument'() {
    when:
      LookSay.atIndex(-1)

    then:
      thrown(IllegalArgumentException)
  }
}