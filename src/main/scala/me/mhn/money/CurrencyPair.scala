package me.mhn.money

case class CurrencyPair[L <: Currency, R <: Currency](left: L, right: R) {
  lazy val invert = new CurrencyPair(right, left)
  override def toString = left + "/" + right

  def -> (rate: Double) = new Rate(this, rate)
}
