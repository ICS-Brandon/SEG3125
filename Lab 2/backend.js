function openNav(){
  if(document.getElementById('sideScroll').style.width != "150px"){
    document.getElementById('sideScroll').style.width= "150px";
    document.getElementById('mainContent').style.marginRight = "150px";
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

function onLoad(){
  /*
  Function gets called
  Need to read JSON File
  Extract Names and display
  */
  var data = $.getJSON(data);
  alert(data.length);
}
