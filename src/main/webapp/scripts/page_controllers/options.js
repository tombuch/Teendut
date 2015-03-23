$(document).ready(function() {





	function enableFacebook(enable) {
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				$("#login_fb").html("Connecté à facebook !")
			}
			else {
				FB.login(function(response) {
					console.log(response);
					if(response.status === "connected")
						$("#login_fb").html("Connecté à facebook !")


				}, {scope: 'public_profile,user_friends'});
			}
		});
	}

	//Activation de facebook
	$("#login_fb").click(function() {
		enableFacebook(true);
	});
	
	
	
	// Change le pseudo de l'utilisateur
	$("#updateNameButton").click(updateName);
	
	// Change le mail de l'utilisateur
	$("#updateMailButton").click(updateEmail);
	
	// Change le mot de passe de l'utilisateur
	$("#updatePasswordButton").click(updatePassword);


	/**
	 * INIT FB SDK
	 */
	window.fbAsyncInit = function() {
		FB.init({
			appId      : '1550153965266129',
			xfbml      : true,
			version    : 'v2.1'
		});
	};

	(function(d, s, id){
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {return;}
		js = d.createElement(s); js.id = id;
		js.src = "http://connect.facebook.net/fr_FR/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));


	if(location.hash == "") {
		$.getJSON("v1/profile/me/" + Cookies["id"], function(data) {
			console.log(data);
			showProfileInfo(data);
		})
		.error(function() {
			// Utilisateur non loggé
			location.replace("/");
		});
	} else {
		$.getJSON("v1/profile/" + location.hash.substring(1), function(data) {
			console.log(data);
			showProfileInfo(data);
		});
	}

});

function updateName() {
	
	console.log("update Name");

	var name = $("#newPseudo").val();
	var name2 = $("#newPseudoConfirmation").val();
	
	
	if (name == name2) {
		$.ajax({
			type : 'PUT',
			contentType : 'application/json',
			url : "v1/users/updateName/" + Cookies["id"] + "/" + name,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				console.log("Pseudo MAJ !");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('postUser error: ' + textStatus);
			}
		});
	}
	
	$("#pseudo_modal").modal('hide');
	
}

function updateEmail() {
	console.log("update Email");

	var mail = $("#newMail").val();
	var mail2 = $("#newMailConfirmation").val();
	
	
	if (mail == mail2) {
		$.ajax({
			type : 'PUT',
			contentType : 'application/json',
			url : "v1/users/updateEmail/" + Cookies["id"] + "/" + mail,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				console.log("email MAJ !");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('postUser error: ' + textStatus);
			}
		});
	}
	
	$("#email_modal").modal('hide');
}

function updatePassword() {
	console.log("update password");
	
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var newPassword2 = $("#newPasswordConfirmation").val();
	
	if (newPassword == newPassword2) {
		$.ajax({
			type : 'PUT',
			contentType : 'application/json',
			url : "v1/users/updatePassword/" + Cookies["id"] + "/" + oldPassword +"/" + newPassword,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				console.log(data.message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('postUser error: ' + textStatus);
			}
		});
	}
	
}

function showProfileInfo(data) {
	$("#info_player").text("");

	$("#info_player").append("<b> Pseudo :</b> " + data.user.name+"  ");
	$("#info_player").append("<input class='btn btn-default btn-xs' type='button' value='Modifier' id='pseudo_button'> <br> <br>");
	$("#info_player").append("<b> Email :</b> " + data.user.email+"  ");
	$("#info_player").append("<input class='btn btn-default btn-xs' type='button' value='Modifier' id='email_button'> <br> <br>");
	$("#info_player").append("<b> Mot de passe :</b> ******  ");
	$("#info_player").append("<input class='btn btn-default btn-xs' type='button' id='password_button' value='Modifier'> <br>");
	
	
	$("#password_button").click(function () { $("#password_modal").modal("show") });
	$("#email_button").click(function() { $("#email_modal").modal("show")});
	$("#pseudo_button").click(function() { $("#pseudo_modal").modal("show")});

}