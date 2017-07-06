/**
 *  form과 관련된 함수 집합
 */
function isNull(obj,msg){
	if(obj.value==""){
		alert(msg);
		obj.focus();
		return true;
	}
	return false;	
}

function checkExt(obj) {
	var forbidName=['exe', 'java', 'jsp', 'js', 'class', 'css'];
	var fileName = obj.value;
	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	
	for(var i = 0; i < forbidName.length; i++) {
		if(forbidName[i] == ext) {
			alert(ext + '확장자는 파일업로드 정책에 위배됩니다');
			return true;
		}
	}
	return false;
}