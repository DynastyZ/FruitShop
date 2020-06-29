<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>管理员登录</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/resource/ml/css/style.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/resource/css/admin.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
	<script src="${ctx}/resource/js/jquery.js" ></script>
	<script src="${ctx}/resource/js/pintuer.js" /></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
	<div class="line bouncein">
		<div class="xs6 xm4 xs3-move xm4-move">
			<div style="height:150px;"></div>
			<div class="media media-y magin-big-bottom"></div>
			<form action="${ctx}/login/toLogin" method="post" >
				<div class="panel login-box" >
					<div class="text-center margin-big padding-big-top"><h1>管理员登录</h1></div>
					<div class="panel-body" style="padding: 30px;padding-bottom: 10px;padding-top: 10px">
						<div class="form-group">
							<div class="field field-icon-right">
								<input type="text" class="input input-big" name="userName" value="admin" placeholder="请输入用户名" data-validate="required:请填写用户名" />
								<span class="icon icon-user margin" ></span>
							</div>
						</div>
						<div class="form-group">
							<div class="field field-icon-right">
								<input type="password" class="input input-big" name="passWord" value="111111" placeholder="请输入密码" data-validate="required:请填写密码" />
								<span class="icon icon-key margin" ></span>
							</div>
						</div>
					</div>
					<div style="padding: 30px"> 
						<input type="submit" class="button button-block bg-main text-big input-big" value="登录">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>





</body>
</html>