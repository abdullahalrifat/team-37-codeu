<!DOCTYPE html>

<html>

  <head>

    <title>Community Page</title>

    <link rel="stylesheet" href="/css/main.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="http://cdn.wysibb.com/js/jquery.wysibb.min.js"></script>
    <link rel="stylesheet" href="http://cdn.wysibb.com/css/default/wbbtheme.css" />

    <script>


      /** Fetches users and adds them to the page. */

      function fetchUserList(){

        const url = '/user-list';

        fetch(url).then((response) => {

          return response.json();

        }).then((users) => {

          const list = document.getElementById('list');

          list.innerHTML = '';


          users.forEach((user) => {

           const userListItem = buildUserListItem(user);

           list.appendChild(userListItem);

          });

        });

      }


      /**
       * Builds a list element that contains a link to a user page, e.g.
       * <li><a href="/user-page.html?user=test@example.com">test@example.com</a></li>
       */

      function buildUserListItem(user){

        const userLink = document.createElement('a');
        userLink.setAttribute('href', '/user-page.html?user=' + user);
        userLink.appendChild(document.createTextNode(user));
        const userListItem = document.createElement('li');
        userListItem.appendChild(userLink);
        return userListItem;

      }


      /** Fetches data and populates the UI of the page. */

      function buildUI(){

       fetchUserList();

      }

    </script>

  </head>

  <body onload="buildUI()">

    <nav>
      <ul id="navigation">
        <li><a href="/">Home</a></li>
        <li><a href="/aboutus.html">About Our Team</a></li>
        <li><a href="/community.jsp">Community</a></li>
      </ul>
    </nav>

    <div id="content">

      <h1>Community Page</h1>

      <p>Here is a list of every user who has posted a message:</p>

      <hr/>

      <ul id="list">Loading...</ul>

    </div>
  </body>
<jsp:include page="unix-time.jsp" /> 
</html>

