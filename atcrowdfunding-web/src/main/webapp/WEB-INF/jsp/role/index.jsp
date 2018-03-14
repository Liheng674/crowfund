<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%@include file="/WEB-INF/jsp/common/userinfo.jsp" %>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="user.html" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="auth_cert.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_project.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a> 
							</li>
							<li style="height:30px;">
								<a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a> 
							</li>
							<li style="height:30px;">
								<a href="process.html"><i class="glyphicon glyphicon-random"></i> 流程管理</a> 
							</li>
							<li style="height:30px;">
								<a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a> 
							</li>
							<li style="height:30px;">
								<a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a> 
							</li>
							<li style="height:30px;">
								<a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a> 
							</li>
							<li style="height:30px;">
								<a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed" >
						<a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" id="queryContent" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteRoles()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/add'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<button type="button" class="btn btn-primary" style="float:right;margin-right:10px;" onclick="window.location.href='${APP_PATH}/role/addBatch'"><i class="glyphicon glyphicon-plus"></i> 批量新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" onclick="selAllBox(this)"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="roleTbody">
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<div id="Pagination" class="pagination"></div>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/jquery/jquery.pagination.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    var pageno = 0;
			    if ( "${param.pageno}" != "" ) {
			    	pageno = parseInt("${param.pageno}")-1;
			    }
			    // 加载数据
			    pageQuery(pageno);
			    
			    $("#queryBtn").click(function(){
			    	var queryContent = $("#queryContent");
			    	if ( queryContent.val() != "" ) {
			    		condflg = true;
			    	}
			    	pageQuery(0);
			    });
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            var condflg = false;
            function pageQuery(pageIndex) {
            	
            	var loadingIndex = 0;
            	var pageno = pageIndex+1;
            	var pagesize = 10;
            	var dataObj = {pageno : pageno, pagesize : pagesize};
            	if ( condflg == true ) {
            		dataObj["queryContent"] = $("#queryContent").val();
            	}
            	
            	$.ajax({
            		type : "POST",
            		url  : "${APP_PATH}/role/pageQuery",
            		data : dataObj,
            		beforeSend : function() {
            			loadingIndex = layer.load(2, {time: 10*1000});
            		},
            		success:function(result){
            			layer.close(loadingIndex);
            			condflg = false;
            			if(result.success) {
            				
            				var rolePage = result.data;
            				var roles = rolePage.datas;
            				var content = "";
            				$.each(roles, function(index, role){
            	                content += '<tr>';
            	                content += '  <td>'+(index+1)+'</td>';
            					content += '  <td><input type="checkbox" value="'+role.id+'"></td>';
            	                content += '  <td>'+role.name+'</td>';
            	                content += '  <td>';
            					content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/role/assign?id='+role.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            					content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/role/edit?pageno='+pageno+'&id='+role.id+'\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            					content += '	  <button type="button" onclick="deleteRole('+role.id+', \''+role.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content += '  </td>';
            	                content += '</tr>';
            				});
            				$("#roleTbody").html(content);
            				
            				$("#Pagination").pagination(rolePage.totalsize, {
            					num_edge_entries: 1, //边缘页数
            					num_display_entries: 4, //主体页数
            					callback: pageQuery,
            					prev_text:"上一页",
            					next_text:"下一页",
            					link_to:"javascript:;",
            					current_page:pageIndex,
            					items_per_page:pagesize //每页显示1项
            				});
            			} else {
                        	layer.msg("角色分页查询失败", {time:2000, icon:5, shift:6}, function(){
                        	});
            			}
            		}
            	});
            }
            
            function deleteRole(id, name) {
    			layer.confirm("删除角色信息【"+name+"】，是否继续？",  {icon: 3, title:'提示'}, function(cindex){

    				$.ajax({
    			    	type : "POST",
    			    	url  : "${APP_PATH}/role/delete",
    			    	data : {id:id},
    			    	success : function(result){
    			    		if ( result.success ) {
    			    			pageQuery(0);
    			    		} else {
                            	layer.msg("角色信息删除失败", {time:2000, icon:5, shift:6}, function(){
                            	});
    			    		}
    			    	}
    			    });
    				layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            
            function selAllBox(box) {
            	// 获取全选框的选中状态
            	var flg = box.checked;
            	// 获取所有角色的复选框
            	var roleBoxes = $("#roleTbody :checkbox");
            	$.each(roleBoxes, function(i, c){
            		c.checked = flg;
            	});
            }
            
            function deleteRoles() {
            	var roleCheckedBoxes = $("#roleTbody :checked");
            	if(roleCheckedBoxes.length == 0) {
                	layer.msg("请选择需要删除的角色信息", {time:2000, icon:5, shift:6}, function(){
                	});
            	} else {
        			layer.confirm("删除选择的角色信息，是否继续？",  {icon: 3, title:'提示'}, function(cindex){
        				var jsonData = {};
        				$.each(userCheckedBoxes, function(i, n){
        					jsonData["ids["+i+"]"] = n.value;
        				});
        				
        				$.ajax({
        			    	type : "POST",
        			    	url  : "${APP_PATH}/role/deletes",
        			    	data : jsonData,
        			    	success : function(result){
        			    		if ( result.success ) {
        			    			pageQuery(0);
        			    		} else {
                                	layer.msg("角色信息删除失败", {time:2000, icon:5, shift:6}, function(){
                                	});
        			    		}
        			    	}
        			    });
        				layer.close(cindex);
        			}, function(cindex){
        			    layer.close(cindex);
        			});
            	}
            }
        </script>
  </body>
</html>

