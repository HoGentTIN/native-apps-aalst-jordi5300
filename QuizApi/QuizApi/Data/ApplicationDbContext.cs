using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using QuizApi.Models;
using System;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace QuizApi.Data
{
        public class ApplicationDbContext : IdentityDbContext
  {
public DbSet<Quiz> Quizzes { get; set; }
public DbSet<Speler> Spelers { get; set; }
public DbSet<Score> _scores { get; set; }
        public DbSet<MeerkeuzeVraag> Questions { get; set; }
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

    }
}

