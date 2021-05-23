<head>
<meta charset="UTF-8">
<title>${param.title }</title>
<link rel="stylesheet" type="text/css" href="css/sample.css">
    <%--Jquery file en methode om show van addfriend te doen--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".knop").click(function(){
                // .test betekend class ="test"
                $(".test").show(1000,function(){
                });
            });
        });
    </script>

</head>
