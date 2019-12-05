using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using QuizApi.DTOs;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace QuizApi.Controllers
{
    [ApiConventionType(typeof(DefaultApiConventions))]
    [Produces("application/json")]
    [Route("api/[controller]")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    [ApiController]
    public class QuizController : Controller
    {
        private readonly IQuizRepository _quizRepository;
        private readonly IScoreRepository _scoreRepository;
        private readonly IMeerkeuzevraagRepository _meerkeuzevraagRepository;

        public QuizController(IQuizRepository context, IScoreRepository context2, IMeerkeuzevraagRepository context3)
        {
            _quizRepository = context;
            _scoreRepository = context2;
            _meerkeuzevraagRepository = context3;
        }
        /// GET: api/Quizzes
        /// <summary>
        /// Get all quizzes ordered by category 
        /// </summary>
        /// <returns>Array of quiz</returns>
        [HttpGet]
        [AllowAnonymous]
        public IEnumerable<Quiz> GetQuizzes()
        {
            return _quizRepository.GetAll().OrderBy(r => r.Categorie);
        }
        // GET: api/Quiz/1
        /// <summary>
        /// Get the quiz with given id
        /// </summary>
        /// <param name="id">the id of the quiz</param>
        /// <returns>The Quiz with the questions included</returns>
        [HttpGet("{id}")]
        [AllowAnonymous]
        public ActionResult<Quiz> GetQuiz(int id)
        {
            Quiz quiz = _quizRepository.GetbyId(id);
            if (quiz == null) return NotFound();
            return quiz;
        }
        // POST: api/Score
        /// <summary>
        /// Adds a new Score with naam and tijd and punten
        /// </summary>
        [HttpPost("Score")]
        public ActionResult<Score> PostScore(ScoreDTO score)
        {
            Score scoreToCreate = new Score() { Id = score.Id, Nicknaam = score.Nicknaam, Punten = score.Punten, Tijd = score.Tijd };
            _scoreRepository.Add(scoreToCreate);
            _scoreRepository.SaveChanges();
            return CreatedAtAction(nameof(GetScore), new { id = scoreToCreate.Id }, scoreToCreate);
        }
        // GET: api/Scores/1
        /// <summary>
        /// Get the score of a quiz with given id
        /// </summary>
        /// <param name="id">the id of the quiz</param>
        /// <returns>The 10 best scores of a quiz</returns>
        [HttpGet("{id}/Scores")]
        [AllowAnonymous]
        public IEnumerable<Score> GetScore(int id)
        {
            IEnumerable<Score> scores = _scoreRepository.GetBy(id);
            return scores;
        }
        // GET: api/Quiz/Meerkeuzevragen/1
        /// <summary>
        /// Get the quiz with given id
        /// </summary>
        /// <param name="id">the id of the quiz</param>
        /// <returns>The Quiz with the questions included</returns>
        [HttpGet("{id}/Meerkeuzevragen")]
        [AllowAnonymous]
        public IEnumerable<MeerkeuzeVraag> GetMeerkeuzevragen(int id)
        {
            IEnumerable<MeerkeuzeVraag> vragen = _meerkeuzevraagRepository.GetbyId(id);
            return vragen;
        }
    }
}
