using Microsoft.EntityFrameworkCore;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Data.Repositories
{
    public class SpelerRepository : ISpelerRepository
    {
        private readonly ApplicationDbContext _context;
        private readonly DbSet<Speler> _spelers;

        public SpelerRepository(ApplicationDbContext dbContext)
        {
            _context = dbContext;
            _spelers = dbContext.Spelers;
        }
        public void Add(Speler speler)
        {
            _context.Add(speler);
        }

        public Speler GetBy(string email)
        {
            return _spelers.SingleOrDefault(c => c.Email == email);
        }

        public void SaveChanges()
        {
            _context.SaveChanges();
        }
    }
}
