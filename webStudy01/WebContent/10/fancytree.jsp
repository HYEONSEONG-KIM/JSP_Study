<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/fancytree/dist/skin-win8/ui.fancytree.css" rel="stylesheet">
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/resources/fancytree/dist/jquery.fancytree-all.min.js"></script>

<script src="<%=request.getContextPath() %>/resources/fancytree/dist/modules/jquery.fancytree.js"></script>

</head>
<body>
<%
	List<String> folderName = (List)request.getAttribute("folderName");	
	List<String> fileName = null;
	Map<String,List<String>> fileMap = (Map)request.getAttribute("fileMap");
	
	System.out.println(folderName);
	
	String folderPtn = "<li id = 'file%s' class = 'folder'>%s";
	String filePtn = "<li><a href = '%s/%s/%s'>%s</a>";
%>
<div id="tree">
    <ul id="treeData" style="display: none;">
		<%
		if(fileMap != null && folderName != null){
			for(int i = 0; i < folderName.size(); i++){
				String folder = folderName.get(i);
				out.print(String.format(folderPtn,folder,folder));
				fileName = fileMap.get(folder);
				out.print("<ul>");
				for(int j = 0; j < fileName.size(); j++){
					String file = fileName.get(j);
					out.print(String.format(filePtn,request.getContextPath(),folder,file,file));	
					System.out.println(String.format(filePtn,request.getContextPath(),folder,file,file));
				}
				out.print("</ul>");
			}
		}
		%>
        
    </ul>
  </div>

  <script type="text/javascript">

    $(function(){

      // using default options

     $("#tree").fancytree({
    	 focus: function(event, data) {
             var node = data.node;
             // Auto-activate focused node after 1 second
             if(node.data.href){
                 node.scheduleAction("activate", 1000);
             }
         },

         activate: function(event, data){
             var node = data.node,
                 orgEvent = data.originalEvent;

             if(node.data.href){
                 //window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" /*node.data.target*/ : node.data.target);
                 window.location.href=node.data.href;    
             }
         },

         click: function(event, data){ // allow re-loads
             var node = data.node,
                 orgEvent = data.originalEvent;

             if(node.isActive() && node.data.href){
                 // data.tree.reactivate();
                 window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" : node.data.target);
             }
         }
     })
     
      
      

    });

  </script>



</body>
</html>