  <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=r9az651hqi"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>GDJLibrary</title>
<script type="text/javascript">
	$(function() {
		initMap();
	});

	function initMap() {
		
		var map = new naver.maps.Map('map', {
	        center: new naver.maps.LatLng(37.478095, 126.879192),
	        zoom: 17
	    });
		
	    var marker = new naver.maps.Marker({
	        map: map,
	        title: "GDJLibrary",
	        position: new naver.maps.LatLng(37.478095, 126.879192),
	        icon: {
	            content: '<img src="<c:url value="/resources/images/map-pin-g894a2ee8c_1920.png"/>" alt="" style="margin: 0px; padding: 0px; border: 0px solid transparent; display: block; max-width: none; max-height: none; -webkit-user-select: none;position: absolute; width: 25px; height: 40px; left: 0px; top: 0px;">',
	            size: new naver.maps.Size(1, 1),
	            anchor: new naver.maps.Point(16, 32)
	        }
	    });
	}
</script>
<style>
	#map{
		width:500px;
		height:400px;
		margin: 100px auto 0;
	}
</style>
</head>
<body>
  
<div id="map"></div>
  
</body>

</html>  
