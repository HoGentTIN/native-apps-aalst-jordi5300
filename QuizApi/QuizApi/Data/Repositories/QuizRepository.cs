using Microsoft.EntityFrameworkCore;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Data.Repositories
{
    public class QuizRepository : IQuizRepository
    {
        private readonly ApplicationDbContext _context;
        private readonly DbSet<Quiz> _quizzes;

        public QuizRepository(ApplicationDbContext dbContext)
        {
            _context = dbContext;
            _quizzes = dbContext.Quizzes;
        }

        public void Add(Quiz quiz)
        {
            _quizzes.Add(quiz);
        }

        public IEnumerable<Quiz> GetAll()
        {
            return _quizzes.ToList();
        }

        public Quiz GetbyId(int id)
        {
            return _quizzes.SingleOrDefault(q => q.Id == id);
        }

    public void Remove(Quiz quiz)
        {
            _quizzes.Remove(quiz);
        }

        public void SaveChanges()
        {
            _context.SaveChanges();
        }

    public bool TryGetQuiz(int id, out Quiz quiz)
    {
      quiz = _context.Quizzes.FirstOrDefault(q => q.Id == id);
      return quiz != null;
    }
  }
}
