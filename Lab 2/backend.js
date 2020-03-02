

function openNav(){
  if(document.getElementById('sideScroll').style.width != "180px"){
    document.getElementById('sideScroll').style.width= "180px";
    document.getElementById('mainContent').style.marginRight = "180px";
  }
  else{
    document.getElementById('sideScroll').style.width="0px";
    document.getElementById('mainContent').style.marginRight="0px";
  }
}

function closeNav(){
  document.getElementById('sideScroll').style.width="0px";
  document.getElementById('mainContent').style.marginRight="0px";
}

function returnHome(){
  window.location = 'Homepage.html';
}

function onLoad(){
  /*
  Function gets called
  Need to read JSON File
  Extract Names and display
  */
  $.getJSON('attack_matrix.json', function(data){
    console.log('it worked');
  });
}

function submitFormEnter(e){
  if(e.keyCode == 13){
    var a = document.forms["aptForm"]["Common Names"].value;
    var b = document.forms["aptForm"]["Alternate Names"].value;
    var c = document.forms["aptForm"]["Target Sector"].value;
    var d = document.forms["aptForm"]["Attack Vector"].value;
    var e = document.forms["aptForm"]["Associated Malware"].value;
    var f = document.forms["aptForm"]["TTPs"].value;
    var g = document.forms["aptForm"]["Modus Operandi"].value;
    var h = document.forms["aptForm"]["References"].value;
    var i = document.forms["aptForm"]["Toolsets"].value;
    if(a==""||c==""||d==""||e==""||f==""||g==""||i==""){
      var alertMessage = errorMessage(a,c,d,e,f,g,i);
      alert(alertMessage);
    }
    else{
      alert("Submitted");
    }
  }
}

function sleep(ms){
  var currentTime = new Date().getTime();

  while(currentTime + ms >= new Date().getTime()){

  }
}

function errorMessage(a,c,d,e,f,g,i){
  var message = "Sorry, you still need to fill out:";
  if(a==""){
    message += " Common Names";
  }
  if(c==""){
    message += " Target Sector";
  }
  if(d==""){
    message += " Attack Vector";
  }
  if(e==""){
    message += " Associated Malware";
  }
  if(f==""){
    message += " TTPs";
  }
  if(g==""){
    message += " Modus Operandi";
  }
  if(i==""){
    message += " Toolsets";
  }
  return message;
}

function submitForm(){
  var a = document.forms["aptForm"]["Common Names"].value;
  var b = document.forms["aptForm"]["Alternate Names"].value;
  var c = document.forms["aptForm"]["Target Sector"].value;
  var d = document.forms["aptForm"]["Attack Vector"].value;
  var e = document.forms["aptForm"]["Associated Malware"].value;
  var f = document.forms["aptForm"]["TTPs"].value;
  var g = document.forms["aptForm"]["Modus Operandi"].value;
  var h = document.forms["aptForm"]["References"].value;
  var i = document.forms["aptForm"]["Toolsets"].value;
  if(a==""||c==""||d==""||e==""||f==""||g==""||i==""){
    var alertMessage = errorMessage(a,c,d,e,f,g,i);
    alert(alertMessage);
  }
  else{
     alert("Submitted");
  }
}
