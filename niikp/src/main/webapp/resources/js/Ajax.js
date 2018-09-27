var request = false;
   try {
     request = new XMLHttpRequest();
   } catch (trymicrosoft) {
     try {
       request = new ActiveXObject("Msxml2.XMLHTTP");
     } catch (othermicrosoft) {
       try {
         request = new ActiveXObject("Microsoft.XMLHTTP");
       } catch (failed) {
         request = false;
       }  
     }
   }

   if (!request)
     alert("Error initializing XMLHttpRequest!");

   var ping = function getCustomerInfo() {
     var work = document.getElementById("work");
     var userId = document.getElementById("userSignIn");
     var url = "/niikp/ajax?work=" + work.innerHTML + "&user=" + userId.innerHTML;
     request.open("GET", url, true);
     request.send(null);
     //request.onreadystatechange = updatePage;
     
   }
   
   setInterval(ping, 10000);
   
   function updatePage() {
     //alert("Server is done!");
	   sendNotification('You have one new assignment!', {
		   body: 'Go to 192.168.1.198/niikp',
		   dir: 'auto'
		   });	   
   }