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
    .star-rating {
  line-height:32px;
  font-size:1.25em;
}

.star-rating .fa-star{color: yellow;}
</style>
<body id="page-top" onload="addLoginOrLogoutLinkToNavigation();">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="">Sleepy Nomads</a>
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
        int guidesCount = (int) request.getAttribute("guidesCount");
        for(int i = 0; i < guidesCount; i++) {
        %>
            <tr>
                <td>${guides.get(i).getName()}</td>
                <td>${guides.get(i).getAddress()}</td>
                <td>${guides.get(i).getContactNo()}</td>
                <td>${guides.get(i).getGender()}</td>
                <td>${guides.get(i).getLocation()}</td>
                <td>${guides.get(i).getCharge()}</td>
                <td>
                <div class="row">
                    <div class="col-lg-12">
                      <div class="star-rating">
                        <span class="fa fa-star-o" data-rating="1"></span>
                        <span class="fa fa-star-o" data-rating="2"></span>
                        <span class="fa fa-star-o" data-rating="3"></span>
                        <span class="fa fa-star-o" data-rating="4"></span>
                        <span class="fa fa-star-o" data-rating="5"></span>
                        <input type="hidden" name="whatever1" class="rating-value" value="5">
                      </div>
                    </div>
                </div>
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

    <script type="text/javascript">

        var $star_rating = $('.star-rating .fa');

        var SetRatingStar = function() {
          return $star_rating.each(function() {
            if (parseInt($star_rating.siblings('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
              return $(this).removeClass('fa-star-o').addClass('fa-star');
            } else {
              return $(this).removeClass('fa-star').addClass('fa-star-o');
            }
          });
        };

        $star_rating.on('click', function() {
          $star_rating.siblings('input.rating-value').val($(this).data('rating'));
          return SetRatingStar();
        });

        SetRatingStar();
        $(document).ready(function() {

        });
    </script>

</body>
</html>