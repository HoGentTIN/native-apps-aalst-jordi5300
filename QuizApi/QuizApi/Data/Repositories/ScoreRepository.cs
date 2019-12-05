using Microsoft.EntityFrameworkCore;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Data.Repositories
{
  public class ScoreRepository : IScoreRepository
  {
    private readonly ApplicationDbContext _context;
    private readonly DbSet<Score> _scores;

    public ScoreRepository(ApplicationDbContext dbContext)
    {
      _context = dbContext;
      _scores = dbContext._scores;
    }
    public void Add(Score score)
    {
      _scores.Add(score);
    }

    public IEnumerable<Score> GetBy(int id)
    {
      return _scores.Where(r => r.QuizId == id).OrderByDescending(r => r.Punten).ThenBy(r => r.Tijd).Take(10).ToList();
    }

    public void SaveChanges()
    {
      _context.SaveChanges();
    }
  }
}
