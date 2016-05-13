$(function(){

var url=window.location.href;


$("#qrcode").qrcode({
  text:url,
  width:120,
  height:120
});

});