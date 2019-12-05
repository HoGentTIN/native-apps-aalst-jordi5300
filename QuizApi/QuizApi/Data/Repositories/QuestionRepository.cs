using Microsoft.EntityFrameworkCore;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Data.Repositories
{
    public class QuestionRepository : IMeerkeuzevraagRepository
    {
        private readonly ApplicationDbContext _context;
        private readonly DbSet<MeerkeuzeVraag> _questions;

        public QuestionRepository(ApplicationDbContext dbContext)
        {
            _context = dbContext;
            _questions = dbContext.Questions;
        }
        public IEnumerable<MeerkeuzeVraag> GetbyId(int quizid)
        {
            return _questions.Where(q => q.QuestionId == quizid);
        }

        public void SaveChanges()
        {
            _context.SaveChanges();
        }
    }
}
