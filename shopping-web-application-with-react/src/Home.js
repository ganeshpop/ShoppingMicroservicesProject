import MovieList from "./MovieList";
import useFetch from "./useFetch";

const Home = () => {

    const {data: movies, isPending, error} = useFetch("http://localhost:8082/movies");

    return (
        <div className="home">
            {isPending && <div>Loading......</div>}
            {error && <div> {error} </div>}
            {movies && <MovieList movies={movies} title="All Movies"/>}
        </div>
    );
}

export default Home;