package me.mhn.money

case class Rate[F <: Currency, T <: Currency](
  from: F,
  to: T,
  rate: Double
) {
  lazy val invert = new Rate(to, from, 1 / rate)
  override def toString = from.toString + "/" + to + ": " + rate
}
