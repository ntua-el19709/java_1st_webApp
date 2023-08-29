/**
 * 
 */

 /**
 * Javascript Document
 */

document.addEventListener('DOMContentLoaded', function(event) {
	
	// Find Element by ID when DOM is ready
	const username = document.querySelector('input[name=username]');
	const passInputField = document.querySelector('input[name=password]');
	
	username.addEventListener("keyup", function(){
		
		var user = username.value;
		// Check
		if(user.length==0){
			const loginbut = document.querySelector('input[name=loginbut]');
			loginbut.disabled=true;
		}
		else{
			const loginbut = document.querySelector('input[name=loginbut]');
			loginbut.disabled=false;
		}
	});	
	
	// Add Click Event Listener
	passInputField.addEventListener("keyup", function(){
		/*
		var user = username.value;
		// Check
		if(user.length==0){
			const loginbut = document.querySelector('input[name=loginbut]');
			loginbut.disabled=true;
		}
		*/
		var pass = passInputField.value;
		// Check
		var ans="Password empty";
		if(pass.length!==0){
			if(RegExp(/^[a-z0-9]+$/i).test(pass))ans="Easy";
			else if(pass.length<8)ans="Medium";
			else ans="Hard";
		}
			
		const checkResponse=ans;
		const hardnessDiv = document.getElementById('hardness');
		hardnessDiv.innerHTML = checkResponse;
	});	
	
})