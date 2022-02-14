# StarWarsListApp
A simple Android application to show a list of all the planets from the Star Wars universe using <a href="https://swapi.dev/">SWAPI</a>. The app shows a list of cards with the name of the planet, terrain and population. The opacity of the card is based on the surface_water (percentage) of each planet.
<p>
The app is meant to demonstrate the usage of the following features:
<ul>
<li>Hilt for dependency injection</li>
<li>Kotlin Coroutines with Flow for asynchronous work, Retrofit</li>
<li>Paging 3 library to solve pagination in the api response</li>
<li>MVVM architecture, arch components</li>
<li>MockK, Junit and Espresso for testing</li>
</ul>
