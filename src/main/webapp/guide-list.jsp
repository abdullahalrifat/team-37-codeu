<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.codeu.data.Guides" %>
<!DOCTYPE html>
<html>
<head>

    <!-- Bootstrap core CSS -->
    <link href="./boostrap/landing-page/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="./boostrap/landing-page/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="./boostrap/landing-page/css/agency.min.css" rel="stylesheet">

</head>
<style type="text/css">
  fieldset, label { margin: 0; padding: 0; }
body{ margin: 20px; }
h1 { font-size: 1.5em; margin: 10px; }

/****** Style Star Rating Widget *****/

.rating { 
  border: none;
  float: left;
}

.rating > input { display: none; } 
.rating > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

.rating > .half:before { 
  content: "\f089";
  position: absolute;
}

.rating > label { 
  color: #ddd; 
 float: right; 
}

/***** CSS Magic to Highlight Stars on Hover *****/

.rating > input:checked ~ label, /* show gold star when clicked */
.rating:not(:checked) > label:hover, /* hover current star */
.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */

.rating > input:checked + label:hover, /* hover current star when changing rating */
.rating > input:checked ~ label:hover,
.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 
</style>
<body id="page-top" onload="addLoginOrLogoutLinkToNavigation();">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">

        <a class="navbar-brand js-scroll-trigger" href="#page-top">Sleepy Nomads</a>

        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto" id="navigation">
            </ul>
        </div>
    </div>
</nav>
<header class="masthead">

        
    <div class="intro-text">
    <h5 > This Table is used to show the information about the guide.This contain the information about the guide (name, address, contact info, locations where he is available, his charge per hour for being a travel guide). Development Ongoing....</h5>
   <table class="table table-dark">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Address</th>
          <th scope="col">Contact No</th>
          <th scope="col">Gender</th>
          <th scope="col">Location</th>
          <th scope="col">Charge</th>
          <th scope="col">Rate</th>
        </tr>
      </thead>


      <tbody>
        <% 
        ArrayList<Guides> guides = (ArrayList<Guides>) request.getAttribute("guides");
        for(Guides guide : guides) {
        %>
            <tr>
                <td><%=guide.getName()%></td>
                <td><%=guide.getAddress()%></td>
                <td><%=guide.getContactNo()%></td>
                <td><%=guide.getGender()%></td>
                <td><%=guide.getLocation()%></td>
                <td><%=guide.getCharge()%></td>
                <td>
                <fieldset class="rating">
                    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                    <input type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                    <input type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                    <input type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                    <input type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                    <input type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                </fieldset>
              </td>
            </tr>
        <% } %>
      </tbody>
    </table>
 </div>


</header>

<!-- Footer -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <span class="copyright">Copyright &copy; Sleepy Nomads 2019</span>
            </div>
            <div class="col-md-4">
                <ul class="list-inline social-buttons">
                    <li class="list-inline-item">
                        <a href="#">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-4">
                <ul class="list-inline quicklinks">
                    <li class="list-inline-item">
                        <a href="#">Privacy Policy</a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">Terms of Use</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>

    <script src="./js/navigation-loader.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</body>
</html>