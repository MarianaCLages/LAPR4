
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshStatus() {
	var request = new XMLHttpRequest();
        var vBoard=document.getElementById("agv");
        
        request.onload = function() {
            vBoard.innerHTML = this.responseText;
            vBoard.style.color="black";
            setTimeout(refreshStatus, 2000);
            };
            
        request.ontimeout = function() {
            vBoard.innerHTML = "Server timeout, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshStatus, 100);
        };
        
        request.onerror = function() { 
            vBoard.innerHTML = "No server reply, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshStatus, 5000);
        };
        
  	request.open("GET", "/info", true);
	request.timeout = 5000;
  	request.send();
	}