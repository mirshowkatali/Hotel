<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
    function submitForm() {
      var postForm = document.forms.postForm;
      postForm.submit();
    }
</script>
</head>
<body onload="submitForm();">
	<div>
		<h2>Payment Gateway Testing Sample</h2>
		<h3>Fill the form and submit it for starting the transaction...</h3>
	</div>

<div>
<table>
	<form name="postForm" action="payU_Process" method="GET" >
	<input type="hidden" name="key" value="gtKFFx" />gtKFFx, 7wjvvX
	<input type="hidden" name="service_provider" value="payu_paisa" />
	
	<tr><td>txnid</td><td><input type="hidden" name="txnid" value="" /></td></tr>
	<tr><td>amount</td><td><input type="text" name="amount" value="${booking.room.price}" /></td></tr>
	<tr><td>firstname</td><td><input type="text" name="firstname" value="${booking.user.firstName}" /></td></tr>
	<tr><td>email</td><td><input type="text" name="email" value="${booking.user.email}" /></td></tr>
	<tr><td>phone</td><td><input type="text" name="phone" value="1234567890" /></td></tr>
	<tr><td>productinfo</td><td><input type="text" name="productinfo" value="test product" /></td></tr>
	<tr><td>success url</td><td><input type="text" name="surl" value="http://127.0.0.1:8080/hms/payU_Success" size="64" /></td></tr>
	<tr><td>failure url</td><td><input type="text" name="furl" value="http://127.0.0.1:8080/hms/payU_Fail" size="64" /></td></tr>
	<tr><td><input type="submit" /></td><td><input type="reset" /></td></tr>
	</form>
</table>
</div>
</body>
</html>