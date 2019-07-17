<% String[] animals = {

     "lions", "tigers", "bears", "lizards", "zebras",

     "kangaroos", "elephants", "cows", "monkeys", "anteaters"}; %>

<!DOCTYPE html>

<html>

  <head>

    <title>JSP Zoo</title>

  </head>

  <body>

    <h1>JSP Zoo</h1>

    <p>Here are the animals we visited at the zoo:</p>

    <ul>

      <% for(int i = 0; i < animals.length; i++){ %>

        <li><%= animals[i] %></li>

      <% } %>

    </ul>

  </body>

</html>
