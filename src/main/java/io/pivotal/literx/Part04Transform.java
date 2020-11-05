package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {


	private Function<User, User> capitalizeUserName = (user) -> new User(
			user.getUsername().toUpperCase(),
			user.getFirstname().toUpperCase(),
			user.getLastname().toUpperCase()
	);


	private User nameToUpperCase(User input){
		return new User(
				input.getUsername().toUpperCase(),
				input.getFirstname().toUpperCase(),
				input.getLastname().toUpperCase()
		);
	}



//========================================================================================

	// TODOx Capitalize the user username, firstname and lastname
	Mono<User> capitalizeOne(Mono<User> mono) {
		return mono.map(capitalizeUserName);
	}



//========================================================================================

	// TODOx Capitalize the users username, firstName and lastName
	Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map(this::nameToUpperCase);
	}

//========================================================================================

	// TODOx Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		return flux.flatMap(this::asyncCapitalizeUser);
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
