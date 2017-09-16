<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- footer CSS -->
<link href="../resources/bootstrap/css/footer.css" rel="stylesheet">
<link
   href="../resources/bootstrap/vendor/font-awesome/css/font-awesome.css"
   rel="stylesheet" type="text/css">
<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.css"
   rel="stylesheet">
<link rel="stylesheet" type="text/css"
   href="//fonts.googleapis.com/earlyaccess/jejumyeongjo.css">
<!-- Theme CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/agency.css">
<link rel="stylesheet"
   href="../resources/bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="../resources/bootstrap/css/style.css">
<link rel="stylesheet" href="../resources/bootstrap/css/material-dashboard.css" />

<!-- jQuery 플러그인 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<title>FoodTruckJoin.jsp</title>

<script type="text/javascript">
   var listText = new Array();
   var listValue = new Array();
   /* 서울 */
   listText[0] = new Array('강남구','관악구','강서구','종로구','용산구','노원구','은평구','마포구','영등포구'); // 각 option의 text
   listValue[0] = new Array('강남구','관악구','강서구','종로구','용산구','노원구','은평구','마포구','영등포구'); // 각 option의 value
   /* 인천 */
   listText[1] = new Array('연수구','남동구','남구','동구','서구','중구');
   listValue[1] = new Array('연수구','남동구','남구','동구','서구','중구');
   /* 경기 */
   listText[2] = new Array('수원시','성남시','고양시','용인시','부천시','안산시','안양시','화성시','의정부','시흥시','파주시');
   listValue[2] = new Array('수원시','성남시','고양시','용인시','부천시','안산시','안양시','화성시','의정부','시흥시','파주시');
   /* 충청도 */
   listText[3] = new Array('천안시','공주시','논산시','아산시');
   listValue[3] = new Array('천안시','공주시','논산시','아산시');
   /* 전라도 */
   listText[4] = new Array('전주시','군산시','익산시','남원시');
   listValue[4] = new Array('전주시','군산시','익산시','남원시');
   /* 강원도 */
   listText[5] = new Array('춘천시','원주시','강릉시','속초시','삼척시');
   listValue[5] = new Array('춘천시','원주시','강릉시','속초시','삼척시');
   /* 대구 */
   listText[6] = new Array('중구','동구','서구','남구','북구','수성구','달서구');
   listValue[6] = new Array('중구','동구','서구','남구','북구','수성구','달서구');
   /* 부산 */
   listText[7] = new Array('중구','서구','동구','영도구','부산진구','동래구','남구','북구','해운대구');
   listValue[7] = new Array('중구','서구','동구','영도구','부산진구','동래구','남구','북구','해운대구');
   /* 울산 */
   listText[8] = new Array('중구','남구','동구','북구');
   listValue[8] = new Array('중구','남구','동구','북구');
   /* 광주 */
   listText[9] = new Array('동구','서구','남구','북구','광산구');
   listValue[9] = new Array('동구','서구','남구','북구','광산구');
   /* 제주도 */
   listText[10] = new Array('제주시','서귀포시');
   listValue[10] = new Array('제주시','서귀포시');
   
   
   function getList(sel_id) {
      var sellerLocation = document.getElementsByName('sellerLocation');
      var sel_id = sel_id - 1;
      if(sel_id >= 0) {
         sellerLocation[1].options.length = listText[sel_id].length;
         for(i = 0; i < listText[sel_id].length; i++) {
            sellerLocation[1].options[i] = new Option(listText[sel_id][i], listValue[sel_id][i]);
         }
      } else {
        sellerLocation[1].options.length = 1;
        sellerLocation[1].options[0].text = "------";
        sellerLocation[1].options[0].value = 0;
      }
   }
</script>

<script>
var count = 1;
function userIdcheck(id){
   
   var userId = id;
   if(userId==""){
       alert("아이디를 입력해주세요");
       return false;
   }

   $.ajax({
      type : 'POST',
        url : 'memberIdCheck.do',   //컨트롤러 전달
        data: { id : id },
        dataType : 'text',   //ajax의 반환값으로 일반 텍스트 (0 혹은 1만 있기에)
        success : function(result){
           if (result == 0) {
              //조회결과 중복 아이디가 없으면
              count = 0;
              $("#result_id_msg").html("사용가능한 아이디 입니다.");
              $("#result_id_msg").css('color', 'skyblue');
            return true;
           }else{
              //조회결과 중복 아이디가 있으면
              $("#result_id_msg").html("사용 불가능한 아이디 입니다.");
              $("#result_id_msg").css('color', 'red');
             count++;
              return false;
           }
        }
   });
   
}
function checkhippen(){
    var x = document.getElementById("sellerPhone");
    var z = document.getElementById("sellerCode");
    x.value = x.value.replace("-","");
    x.value = x.value.replace("/","");
    x.value = x.value.replace("=","");
    z.value = z.value.replace("-","");
    z.value = z.value.replace("/","");
    z.value = z.value.replace("=","");
}

function checkForm(){
   var regular = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
   var regularCode = /^[0-9]+$/
   var form = document.sellerFrm;
 
     if(count>0){
       alert("아이디 중복체크 하세요");
        return false;
     }
    if(!form.sellerId.value){
       alert("아이디를 입력해주세요");
       form.sellerId.focus();
       return false;
    }
    else if(!form.sellerPw.value){
       alert("비밀번호를 입력해주세요");
       form.sellerPw.focus();
       return false;
    }
    else if(form.sellerPw.value != form.userPwCheck.value){
       alert("비밀번호가 일치하지 않습니다.");
       form.userPwCheck.focus();
       return false;
    }
    else if(!form.sellerName.value){
       alert("이름를 입력해주세요");
       form.sellerName.focus();
       return false;
    }
    else if(!form.sellerEmail.value){
       alert("이메일을 입력해주세요");
       form.sellerEmail.focus();
       return false;
    }
    else if(!regular.test($("#sellerEmail").val())){
         alert("이메일 형식에 맞게 입력하세요.XXX@fooco.com");
         $("sellerEmail").focus();
         return false;
      }
    else if(!form.sellerPhone.value){
       alert("핸드폰을 입력해주세요");
       form.memberPhone.focus();
       return false;
    }
    else if(!form.sellerCode.value){
       alert("사업자 번호를 입력해주세요");
       form.sellerCode.focus();
       return false;
    }
    else if(!regularCode.test($("#sellerCode").val())){
    	 alert("사업자 번호는 숫자로만 되어 있어야 합니다.");
         $("sellerCode").focus();
         return false;
    }
    else if(!form.codeName.value){
       alert("사업자 대표이름을 입력해주세요");
       form.codeName.focus();
       return false;
    }
   
 return true;
}
</script>
<style> 
 .pricing_price_border { 
     background-color: #ff6863;
     padding: 10px; 
     border-radius: 50%; 
     margin: 0 auto; 
     width: 150px; 
     height: 150px; 
     position: absolute; 
     top: 11%; 
     left: 25%; 
     right: 25%; 
 } 
</style>





</head>
<body style="background-color: #f2f2f2;" class="bg-light-gray">
   <nav id="mainNav"
      class="navbar navbar-default navbar-custom navbar-fixed-top"
      style="background-color:black;">
   <div class="container">
      <!-- 로고와 임시 사이트명 -->
      <div class="col-md-2">
         <img src="../resources/bootstrap/img/logos/logo.png"
            style="width: 150px; height: 53px; margin-right: 50px;" />
      </div>
      <div class="col-md-3">
         <a style="font-size: 3em; color: yellow;"
            class="navbar-brand page-scroll" href="../index.do"> FooCo
            &nbsp; </a>
      </div>
      <!-- 로고 끝 -->
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header page-scroll">
         <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span> Menu <i
               class="fa fa-bars"></i>
         </button>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse"
         id="bs-example-navbar-collapse-1">
         <ul class="nav navbar-nav navbar-right">
            <li class="hidden"><a href="#page-top"></a></li>
            <li><a class="page-scroll" href="memberLogin.do" data-toggle="modal"
               style="color: white;">로그인</a></li>

            <li><a class="page-scroll" href="memberJoin.do">회원가입</a></li>
         </ul>
      </div>
      <!-- /.navbar-collapse -->
   </div>
   <!-- /.container-fluid --> </nav>

   <div class="container" style="margin-top:;">
      <div class="row">
         <div class="main_pricing roomy-100">
            <div class="col-md-offset-2 col-md-8 col-sm-12">
               <div class="pricing_item sm-m-top-30">
                       <div class="pricing_top_border"></div>
                    <div class="pricing_head p-top-30 p-bottom-10 text-center">
                        <div class="head_title text-center">
                   			 <h2>FooCo Member Join</h2>
                		</div>
                    </div>
                          
                          <div class="pricing_body bg-white p-top-10 p-bottom-60"> 

                             <form class="form-horizontal" name="sellerFrm" action="sellerJoin.do" method="post">
                               
                            <div class="card" style="width: 800px; margin: 0;">
                              <div  style="margin: 0">
                                 <div class="form-group">
                                    <label class="col-sm-3 " for="sellerId" style="color:black; font-weight:bold; font-size:15px;"><strong>아이디
                                    </strong></label>
                                    <div class="col-sm-4">
                                       <input class="form-control" name="sellerId" id="sellerId"
                                          type="text" style="width: 240px;" placeholder="아이디">
                                       <span id="result_id_msg" style="font-size: 1em;"></span>
                                    </div>
                                    <div class="col-sm-3">
                                       <button type="button" class="btn btn-info btn-xs pull-left"
                                          onclick="return userIdcheck(this.form.sellerId.value)"
                                          style="display: inline-block; color:black; font-weight:bold; font-size:15px;"">중복확인</button>
                                    </div>
                                 </div>
                              </div>

                              <div id="userIdCheckTrue">
                                 <div  style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerPw" style="color:black; font-weight:bold; font-size:15px;"><strong>비밀번호</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control control" name="sellerPw"
                                             id="sellerPw" type="password" placeholder="비밀번호">
                                          <p class="help-block">숫자, 특수문자 포함시 더욱 안전합니다.</p>
                                       </div>
                                    </div>

                                    <div class="form-group" style="margin-bottom: 20px">
                                       <label class="col-sm-3" for="userPwCheck" style="color:black; font-weight:bold; font-size:15px;"><strong>비밀번호확인</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control" id="userPwCheck"
                                             type="password" placeholder="비밀번호 확인">
                                          <p class="help-block">비밀번호를 한번더 입력해주세요</p>
                                       </div>
                                    </div>
                                 </div>
							</div>
                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerName" style="color:black; font-weight:bold; font-size:15px;"><strong>이름</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control" name="sellerName"
                                             id="sellerName" type="text" placeholder="이름">
                                       </div>
                                    </div>
                                 </div>

                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerEmail" style="color:black; font-weight:bold; font-size:15px;"><strong>이메일</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control" name="sellerEmail"
                                             id="sellerEmail" type="text" placeholder="이메일">
                                       </div>
                                    </div>
                                 </div>

                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerPhone" style="color:black; font-weight:bold; font-size:15px;"><strong>휴대폰번호</strong></label>
                                       <div class="col-sm-6" style="margin-bottom: 20px">

                                          <input type="text" class="form-control" name="sellerPhone"
                                             id="sellerPhone" placeholder="- 없이 입력해 주세요"
                                             onkeyup="checkhippen()" />

                                          <p class="help-block">[ex] 01012345678</p>
                                       </div>
                                    </div>
                                 </div>
                                   <div class="pricing_top_border"></div>
                                <div class="pricing_head p-top-30 p-bottom-10 text-center">
			                        <div class="head_title text-center">
			                   			 <h2>판매자 추가정보</h2>
			                		</div>
			                    </div>
                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerCode"><strong style="color:black; font-weight:bold; font-size:15px;">사업자 번호</strong></label>
                                       <div class="col-sm-6" style="margin-bottom: 20px">
                                             <input class="form-control" style="display:inline" name="sellerCode" id="sellerCode" type="text" placeholder="- 없이 입력해 주세요" maxlength=10  onkeyup="checkhippen()" >
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="codeName" style="color:black; font-weight:bold; font-size:15px;"><strong>사업자 대표이름</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control" name="codeName"   id="codeName" type="text" >
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerLocation" style="color:black; font-weight:bold; font-size:15px;"><strong>주 사업지역</strong></label>
                                       <div class="col-sm-6">
                                          <select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
                                             <option value="">선택</option>
                                             <option value="서울">서울</option>
                                             <option value="인천">인천</option>
                                             <option value="경기">경기</option>
                                             <option value="충청도">충청도</option>
                                             <option value="전라도">전라도</option>
                                             <option value="강원도">강원도</option>
                                             <option value="대구">대구</option>
                                             <option value="부산">부산</option>
                                             <option value="울산">울산</option>
                                             <option value="광주">광주</option>
                                             <option value="제주">제주</option>
                                          </select>
                                             
                                          <select name="sellerLocation" class="form-control">
                                             <option value="">------</option>
                                          </select>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="card" style="margin: 0">
                                    <div class="form-group">
                                       <label class="col-sm-3" for="sellerBlogaddr" style="color:black; font-weight:bold; font-size:15px;"><strong>개인 블로그<br/> 사이트 주소</strong></label>
                                       <div class="col-sm-6">
                                          <input class="form-control" name="sellerBlogaddr" id="sellerBlogaddr" type="text" value="http://">
                                       </div>
                                    </div>
                                 </div>
                                 
                                
                                <div class="pricing_btn text-center m-top-40">
                                    <button type="submit" class="btn btn-primary"
                                       onclick="return checkForm()">가입</button>
                                    <button type="reset" class="btn btn-worning">초기화</button>
                     
                              </div>
                      
                             </form>
                          </div>
                       </div>
               </div>
            </div>
         </div>
         <!-- End off col-md-4 -->
      </div>
   <!--End off row-->
   </div>
   <!--End off container -->
   
   <!-- footer -->
   <div class="container">
      <div class="row">
         <div class="col-md-4 text-center" style="margin-top: 15px;">
            <span>Copyright &copy; Website 2017.09.11</span>
         </div>
         <div class="col-md-4 text-center">
            <ul class="list-inline social-buttons">
               <li>
                  <p
                     style="font-family: 'Kaushan Script', 'Helvetica Neue', Helvetica, Arial, cursive; font-size: 2em;">
                     FooCo</p>
               </li>
               <li><a href="#"><i class="fa fa-facebook fa-2x"></i></a></li>

            </ul>
         </div>
         <div class="col-md-4 text-center" style="margin-top: 5px;">
            <ul class="list-inline quicklinks">
               <li>Creator</li>
               <li>송영민</li>
               <li>유영민</li>
               <li>이형무</li>
               <li>서동섭</li>
               <li>김태영</li>
            </ul>
         </div>
      </div>
   </div>
   <!-- footer end -->
   <!-- jQuery -->
   <!-- <script src="../resources/bootstrap/vendor/jquery/jquery.js"></script> -->

   <!-- Bootstrap Core JavaScript -->
   <!-- <script src="../resources/bootstrap/vendor/bootstrap/js/bootstrap.js"></script> -->

   <!-- Plugin JavaScript -->
   <!-- <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"
      integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb"
      crossorigin="anonymous"></script>
 -->

   <!-- Theme JavaScript -->
   <!-- <script src="../resources/bootstrap/js/agency.js"></script> -->

   <!--   Core JS Files   -->
   <!-- <script src="../resources/bootstrap/js/jquery-3.1.0.min.js"
      type="text/javascript"></script>
   <script src="../resources/bootstrap/js/bootstrap.min.js"
      type="text/javascript"></script>
   <script src="../resources/bootstrap/js/material.min.js"
      type="text/javascript"></script> -->

   <!--  Charts Plugin -->
   <!-- <script src="../resources/bootstrap/js/chartist.min.js"></script> -->

   <!--  Notifications Plugin    -->
   <!-- <script src="../resources/bootstrap/js/bootstrap-notify.js"></script> -->

   <!--  Google Maps Plugin    -->
   <!-- <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js"></script>

   Material Dashboard javascript methods
   <script src="../resources/bootstrap/js/material-dashboard.js"></script>

   Material Dashboard DEMO methods, don't include it in your project!
   <script src="../resources/bootstrap/js/demo.js"></script>
 -->
</body>
</html>