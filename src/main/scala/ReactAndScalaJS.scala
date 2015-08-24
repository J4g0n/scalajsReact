package reactAndScala.webapp

import japgolly.scalajs.react.{React, ReactComponentB}

import scala.scalajs.js.JSApp

import org.scalajs.dom
import org.scalajs.dom.{Element, document}
import org.scalajs.jquery.jQuery

import scala.scalajs.js.annotation.JSExport
import japgolly.scalajs.react.vdom.prefix_<^._


@JSExport
object ReactAndScalaJS {
  @JSExport
  def main(htmlNode: Element): Unit = {
    val HelloMessage = ReactComponentB[Unit]("HelloMessage")
      .render(_ => <.div("Hello from Scala and React"))
      .buildU

    React.render(HelloMessage(), htmlNode)
  }
}
