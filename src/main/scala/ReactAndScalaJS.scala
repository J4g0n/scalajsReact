package reactAndScala.webapp

import japgolly.scalajs.react.{React, ReactComponentB}

import scala.scalajs.js.JSApp

import org.scalajs.dom
import dom.document
import org.scalajs.jquery.jQuery

import scala.scalajs.js.annotation.JSExport
import japgolly.scalajs.react.vdom.prefix_<^._


object ReactAndScalaJS extends JSApp {
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  def setupUI(): Unit = {
    jQuery("body").append("<p>Jquery Hello World</p>")
    //jQuery("#click-me-button").click(addClickedMessage _)
    jQuery("""<button type="button">Click me!</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))
  }

  @JSExport
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def nameList(name: String) = {
    <.div(
      ^.name := "hello in react",
      "React say hello to " + name
    )
  }

  def main: Unit = {
    println("Hello!")
    appendPar(document.body, "Hello World")
    jQuery(setupUI _)
    jQuery("body").append("<p>Cool message</p>")
    val TestComponent = ReactComponentB[String]("TestComponent")
      .render(name => nameList(name))
      .build

    React.render(TestComponent("Simon"), document.body)
  }
}
