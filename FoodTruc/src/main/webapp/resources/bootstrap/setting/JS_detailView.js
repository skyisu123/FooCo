var $j = jQuery.noConflict();

$j(document).ready(function(){
	$("#homeButton").trigger("click");
    goReviewPage(1); //현재 페이지를 1로 설정하여 ajax 호출
   
    $j('#lightSlider').lightSlider({
		auto: true,
        pauseOnHover: true,
        gallery: true,
        item: 1,
        loop:true,
        slideMargin: 0,
        thumbItem: 9
    });   
});

function initMap() {
	var uluru = {lat: 37.478655537, lng: 126.8786681126};
	var map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 18,
	  center: uluru
	});
	var marker = new google.maps.Marker({
	  position: uluru,
	  map: map
	});
}

function replyWrite(){	// 한줄평 등록 함수
	var frm = document.replyFrm;
	var contentf = frm.replyContent;
	var score = frm.score.value;
	var memberId = '<%=memberId%>';
	
	// 폼 값 유효성 체크
	if(contentf.value == ""){
		alert("한줄평 내용을 입력해주세요.");
		content.focus();
		return false;
	}
	else{
		var replyContent = contentf.value;
		
		$.ajax({
			type : 'post',
			url : '../reply/insertReview.do',
			data : {"replyContent" : replyContent , "score" : score, "truckNum" : '${foodTruckVo.truckNum}', "memberId" : memberId },
			success : function(data){
				//alert("성공!");
				if(data == 1) goReviewPage(1);
			},
			error:function(request,status,error){
	        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
	        }
		});
		
	}
	//alert(frm.replyContent.value + " " + frm.score.value);
}

function goReviewPage(currentPage){
	$.ajax({
		type : 'get',
		url : '../reply/replyTruckList.do?currentPage=' + currentPage + '&truckNum=${foodTruckVo.truckNum}',
		success : function(data){
			//alert(data);
			$("#reviewSection").html(data);
		}
	});
}

function updateLike(){
	$.ajax({
		type: 'post',
		url : '../truck/updateTruckLike.do',
		data : {"truckNum" : '${foodTruckVo.truckNum}'},
		success : function(data){
			if(data != -1){
				$("#likeNum").html(data);
				$("#likeSpan").html(data);
				$("#likeBtn").attr("onclick", "deleteLike();");
				$("#likeImg").attr("src", "../resources/bootstrap/img/싫어요.PNG");
			}
			else $("#likeTd").html("불러오는 도중 오류가 발생했습니다.");
		},
		error:function(request,status,error){
        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
        }
	});
}
function deleteLike(){
	$.ajax({
		type: 'post',
		url : '../truck/deleteTruckLike.do',
		data : {"truckNum" : '${foodTruckVo.truckNum}'},
		success : function(data){
			if(data != -1){
				$("#likeNum").html(data);
				$("#likeSpan").html(data);
				$("#likeBtn").attr("onclick", "updateLike();");
				$("#likeImg").attr("src", "../resources/bootstrap/img/캡처.PNG");
			}
			else $("#likeTd").html("불러오는 도중 오류가 발생했습니다.");
		},
		error:function(request,status,error){
        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
        }
	});
}