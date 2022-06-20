package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//Anotación para indicar con que ejecutor de pruebas se va a realizar
@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule() //Regla de prueba -  indica que lo posterior debe ejecutarse antes de cada prueba de la clase.
    //ActivityScenarioRuleproviene de la biblioteca de prueba de AndroidX. Le indica al dispositivo que inició una actividad especificada por el desarrollador.
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        // onView() recibe un ViewMatcher que es es un componente de IU
        // que coincide con el ID que se le pasa.
        //onView() retorna un ViewInteraction, que es un objeto con el
        // que podemos interactuar como si estuviéramos controlando el dispositivo
        onView(withId(R.id.cost_of_service_edit_text))
            //ViewInteraction lama su método perform() que recibe un ViewAction
                //Este se usa para ingresar texto, con typeText() le indicamis el texto a ingresar
                //Y este recibe el dato a ingresar
            .perform(typeText("50.00"))

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
                //check(),recibe un ViewAssertion
                //que es una aserción especial de Espresso que se usa para los componentes de IU.
                //La aserción es que el contenido de TextViewcoincide con el texto que contiene la cadena "$10.00".
            .check(matches(withText(containsString("$10.00"))))

    }

    @Test
    fun calculate_18_percent_tip(){
        //Se ingresa el costo
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        //Se selecciona el 18%
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        //Se da click en calcular
        onView(withId(R.id.calculate_button)).perform(click())
        //Se realiza la aserción
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$9.00"))))

    }


    @Test
    fun calculate_15_percent_tip(){
        //Se ingresa el costo
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        //Se selecciona el 18%
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        //Se da click en calcular
        onView(withId(R.id.calculate_button)).perform(click())
        //Se realiza la aserción
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$8.00"))))
    }

    @Test
    fun round_tip_15_percent(){
        //Se ingresa el costo
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        //Se selecciona el 18%
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        //Se selecciona con redondeo
        onView(withId(R.id.round_up_switch)).perform(click())
        //Se da click en calcular
        onView(withId(R.id.calculate_button)).perform(click())
        //Se realiza la aserción
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$7.50"))))
    }
}